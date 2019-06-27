package com.cep.corporateeventplanner.controller;

import com.cep.corporateeventplanner.model.*;
import com.cep.corporateeventplanner.service.RoleService;
import com.cep.corporateeventplanner.service.UserService;
import io.swagger.annotations.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    RoleService roleService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenStore tokenStore;

    @ApiOperation(value = "Handle sign up of a new user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully create new user", response = User.class),
            @ApiResponse(code = 404, message = "Could not create user", response = ErrorDetail.class)
    })
    @PostMapping(value = "/signup")
    public ResponseEntity<?> handleSignup(@RequestBody User user){
        Role role = roleService.findByName("USER");
        User newUser = new User(user.getUsername(), user.getPassword(), new ArrayList<>(Arrays.asList(new UserRoles(new User(), role))));
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());
        newUser.setImage(user.getImage());
        newUser.setUserEvents(user.getUserEvents());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "sign in of a user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully logged in", response = User.class),
            @ApiResponse(code = 401, message = "User not authorized", response = ErrorDetail.class),
            @ApiResponse(code = 404, message = "User not found", response = ErrorDetail.class)
    })
    @PostMapping(value = "/login")
    public ResponseEntity<?> handleSignin(@RequestBody User user, HttpServletRequest request){
        String username = user.getUsername();
        //user.setPassword(user.getPassword());
        String password = user.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

/*        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        authenticationManager.authenticate(token);*/

        if (token.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(token);
            return new ResponseEntity<>(token , HttpStatus.OK);
        }else{
            throw new AuthenticationServiceException("Failed to authenticate user. Check Login credentials");
        }
    }

    @ApiOperation(value = "Handle the logging out of a user", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged out", response = void.class)
    })
    @GetMapping(value = "/oauth/revoke-token")
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null)
        {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }
    @ApiOperation(value = "Get the current user", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully accessed the user info", response = User.class),
            @ApiResponse(code = 500, message = "Failed to access user info", response = ErrorDetail.class)
    })
    @GetMapping(value = "/users/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication){
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a current user", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated user", response = void.class),
            @ApiResponse(code = 500, message = "Failed to update user", response = ErrorDetail.class)
    })
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                User updateUser,
                                        @PathVariable
                                                long id)
    {
        userService.update(updateUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @ApiOperation(value = "Delete a current user", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesfully deleted ", response = void.class),
            @ApiResponse(code = 500, message = "Failed to delete user", response = ErrorDetail.class)
    })
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request,
                                            @PathVariable
                                            long id)
    {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
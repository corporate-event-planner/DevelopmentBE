package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.model.User;
import com.cep.corporateeventplanner.model.UserEvents;
import com.cep.corporateeventplanner.model.UserRoles;
import com.cep.corporateeventplanner.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository repo;

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(userList::add);
        return userList;
    }

    @Override
    public User findUserById(long id) {
        return repo.findById(id).orElseThrow(EntityExistsException::new);
    }

    @Override
    public void delete(long id) {
        if(repo.findById(id).isPresent()){
            repo.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }
    }
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = repo.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }

    @Override
    public User save(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setCompanyname(user.getCompanyname());
        newUser.setEmail(user.getEmail());
        newUser.setImage(user.getImage());
        newUser.setRole(user.getRole());

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getUserRoles())
        {
            newRoles.add(new UserRoles(newUser, ur.getRole()));
        }
        newUser.setUserRoles(newRoles);

        ArrayList<UserEvents> newEvents = new ArrayList<>();
        for (UserEvents event: user.getUserEvents()){
            newEvents.add(new UserEvents(newUser, event.getEvent()));
        }

        return repo.save(newUser);
    }

    @Override
    public User update(User user, long id) {
        User currentUser = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        if (user.getCompanyname() != null){
            currentUser.setCompanyname(user.getCompanyname());
        }
        if (user.getEmail() != null){
            currentUser.setEmail(user.getEmail());
        }
        if (user.getUserEvents() != null && user.getUserEvents().size() > 0){
            for (UserEvents event: user.getUserEvents()){
                currentUser.getUserEvents().add(new UserEvents(currentUser, event.getEvent()));
            }
        }
        if (user.getImage() != null){
            currentUser.setImage(user.getImage());
        }
        if (user.getRole() != null){
            currentUser.setRole(user.getRole());
        }
        if (user.getPassword() != null){
            currentUser.setPassword(user.getPassword());
        }
        if (user.getUsername() != null){
            currentUser.setUsername(user.getUsername());
        }
        repo.save(currentUser);
        return currentUser;
    }

    @Override
    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
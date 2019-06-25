package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.model.Event;
import com.cep.corporateeventplanner.model.User;
import com.cep.corporateeventplanner.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

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

    @Override
    public User save(User user) {
        repo.save(user);
        return user;
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
        if (user.getEventlist() != null && user.getEventlist().size() > 0){
            for (Event event: user.getEventlist()){
                currentUser.getEventlist().add(event);
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
}

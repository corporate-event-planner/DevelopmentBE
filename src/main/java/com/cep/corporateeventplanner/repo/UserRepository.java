package com.cep.corporateeventplanner.repo;

import com.cep.corporateeventplanner.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
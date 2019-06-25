package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.model.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    void delete(long id);

    Role save(Role role);

    Role findByName(String name);

    void insertUserRoles(long userid, long roleid);
}
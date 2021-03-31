package com.business.application.service;

import com.business.application.entity.Role;

import java.util.List;

public interface RoleService {
    Role findRoleByRoleName(String roleName);

    List<Role> findAllRoles();
}

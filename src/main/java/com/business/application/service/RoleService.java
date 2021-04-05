package com.business.application.service;

import com.business.application.entity.Role;
import com.business.application.enumerations.RoleType;

import java.util.List;

public interface RoleService {
    Role findRoleByRoleName(RoleType roleName);

    List<Role> findAllRoles();

    void saveAndFlushRoles();
}

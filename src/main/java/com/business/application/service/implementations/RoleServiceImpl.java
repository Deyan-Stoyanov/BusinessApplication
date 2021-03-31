package com.business.application.service.implementations;

import com.business.application.entity.Role;
import com.business.application.repository.RoleRepository;
import com.business.application.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return this.roleRepository.findRoleByRoleName(roleName).orElse(null);
    }

    @Override
    public List<Role> findAllRoles() {
        return this.roleRepository.findAll();
    }
}

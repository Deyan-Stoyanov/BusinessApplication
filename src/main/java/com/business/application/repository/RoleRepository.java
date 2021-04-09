package com.business.application.repository;

import com.business.application.entity.Role;
import com.business.application.enumerations.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findRoleByRoleName(RoleType roleName);

}

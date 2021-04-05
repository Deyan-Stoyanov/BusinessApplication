package com.business.application.entity;

import com.business.application.enumerations.RoleType;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    private RoleType roleName;

    public Role() {
    }

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    public RoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleType roleName) {
        this.roleName = roleName;
    }

    @Override
    @Transient
    public String getAuthority() {
        return this.getRoleName().name();
    }
}

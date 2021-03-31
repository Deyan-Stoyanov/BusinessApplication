package com.business.application.entity;

import com.business.application.enumerations.RoleType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private RoleType roleName;

    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "roleName", nullable = false)
    public RoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleType roleName) {
        this.roleName = roleName;
    }
}

package com.business.application.entity.view;

import com.business.application.entity.Role;

import java.util.Collection;

public class UserViewModel {

    private String id;
    private String email;
    private String username;
    private boolean accountNonLocked;
    private EmployeeViewModel employee;
    private Collection<Role> authorities;

    public UserViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Collection<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Role> authorities) {
        this.authorities = authorities;
    }

    public EmployeeViewModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeViewModel employee) {
        this.employee = employee;
    }
}

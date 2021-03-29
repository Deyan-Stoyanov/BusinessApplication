package com.business.application.entity.binding;

import javax.enterprise.inject.Model;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Model
public class UserRegisterBindingModel {

    private String email;
    private String username;
    private String password;
    private String repeatPassword;

    @NotBlank(message = "{notblank.registration.email}")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "{notblank.registration.username}")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "{notblank.registration.password}")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = "{notblank.registration.repeatPassword}")
    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}

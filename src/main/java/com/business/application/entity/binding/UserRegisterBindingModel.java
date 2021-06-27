package com.business.application.entity.binding;

import javax.enterprise.inject.Model;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Model
public class UserRegisterBindingModel {

    private String email;
    private String username;
    private String password;
    private String repeatPassword;

    public UserRegisterBindingModel() {
    }

    @NotBlank(message = "Полето е задължително!")
    @Size(min = 5, max = 50, message = "Полето трябва да бъде между 5 и 50 символа!")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@.+\\..+$", message = "Полето не е валиден имейл адрес!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "Полето е задължително!")
    @Size(min = 3, max = 30, message = "Полето трябва да съдържа между 3 и 30 символа!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "Полето е задължително!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Полето трябва да съдържа поне 8 символа, от които поне една буква, едно число и специален символ!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = "Полето е задължително!")
    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}

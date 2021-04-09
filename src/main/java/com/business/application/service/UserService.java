package com.business.application.service;

import com.business.application.entity.User;
import com.business.application.entity.binding.UserRegisterBindingModel;
import com.business.application.exceptions.CreateAccountException;
import org.springframework.validation.BindingResult;

public interface UserService {
    void doRegisterAccount(UserRegisterBindingModel userModel, BindingResult bindingResult) throws CreateAccountException;

    void doDeleteAccount(String email) throws CreateAccountException;

    User findUserById(String id);

    void saveAndFlushUser(User user);
}

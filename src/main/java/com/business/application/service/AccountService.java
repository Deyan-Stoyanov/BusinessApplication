package com.business.application.service;

import com.business.application.entity.binding.UserRegisterBindingModel;
import com.business.application.exceptions.CreateAccountException;
import org.springframework.validation.BindingResult;

public interface AccountService {
    void doRegisterAccount(UserRegisterBindingModel userModel, BindingResult bindingResult) throws CreateAccountException;

    void doDeleteAccount(String email) throws CreateAccountException;
}

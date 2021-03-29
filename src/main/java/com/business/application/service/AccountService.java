package com.business.application.service;

import com.business.application.entity.binding.UserRegisterBindingModel;
import org.springframework.validation.BindingResult;

public interface AccountService {
    void doRegisterAccount(UserRegisterBindingModel userModel, BindingResult bindingResult);
}

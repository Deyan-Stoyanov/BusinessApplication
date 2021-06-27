package com.business.application.service;

import com.business.application.entity.User;
import com.business.application.entity.binding.UserRegisterBindingModel;
import com.business.application.entity.view.UserViewModel;
import com.business.application.exceptions.CreateAccountException;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {
    void doRegisterAccount(UserRegisterBindingModel userModel, BindingResult bindingResult) throws CreateAccountException;

    void doDeleteAccount(String email) throws CreateAccountException;

    User findUserById(String id);

    void saveAndFlushUser(User user);

    void doDeleteAccountByEmployeeId(String id);

    List<UserViewModel> findAllUsers();

    void deleteById(String id);
}

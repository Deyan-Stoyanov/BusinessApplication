package com.business.application.service.implementations;

import com.business.application.constants.Constants;
import com.business.application.entity.binding.UserRegisterBindingModel;
import com.business.application.repository.UserRepository;
import com.business.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void doRegisterAccount(UserRegisterBindingModel userModel, BindingResult bindingResult) {
        Boolean isInputValid = validateRegistrationInput(userModel, bindingResult);
        if (isInputValid){
            // TODO: 30.3.2021 г. Implement registration logic
        }
    }

    private Boolean validateRegistrationInput(UserRegisterBindingModel userModel, BindingResult bindingResult) {
        if (userModel.getPassword() == null || userModel.getRepeatPassword() == null ||
                userModel.getPassword().equals(userModel.getRepeatPassword())){
            bindingResult.rejectValue("repeatPassword", Constants.PASSWORDS_NOT_MATCH_MESSAGE);
        }
        if (userRepository.findUserByEmail(userModel.getEmail()).isPresent()){
            bindingResult.rejectValue("email", Constants.EMAIL_ALREADY_EXISTS_MESSAGE);
        }
        if (userRepository.findUserByUsername(userModel.getUsername()).isPresent()){
            bindingResult.rejectValue("username", Constants.USERNAME_ALREADY_EXISTS_MESSAGE);
        }
        return !bindingResult.hasErrors();
    }
}

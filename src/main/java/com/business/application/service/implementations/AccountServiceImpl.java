package com.business.application.service.implementations;

import com.business.application.constants.Constants;
import com.business.application.entity.Role;
import com.business.application.entity.User;
import com.business.application.entity.binding.UserRegisterBindingModel;
import com.business.application.enumerations.RoleType;
import com.business.application.exceptions.CreateAccountException;
import com.business.application.repository.UserRepository;
import com.business.application.service.AccountService;
import com.business.application.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    @Override
    public void doRegisterAccount(UserRegisterBindingModel userModel, BindingResult bindingResult) throws CreateAccountException {
        Boolean isInputValid = validateRegistrationInput(userModel, bindingResult);
        if (isInputValid) {
            try {
                this.registerAccountIntern(userModel);
            } catch (Exception e) {
                throw new CreateAccountException();
            }
        }
    }

    @Override
    public void doDeleteAccount(String email) throws CreateAccountException {
        try {
            this.userRepository.deleteByEmail(email);
        } catch (Exception e) {
            throw new CreateAccountException();
        }
    }

    private Boolean validateRegistrationInput(UserRegisterBindingModel userModel, BindingResult bindingResult) {
        if (userModel.getPassword() == null || userModel.getRepeatPassword() == null ||
                userModel.getPassword().equals(userModel.getRepeatPassword())) {
            bindingResult.rejectValue("repeatPassword", Constants.PASSWORDS_NOT_MATCH_MESSAGE);
        }
        if (userRepository.findByEmail(userModel.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", Constants.EMAIL_ALREADY_EXISTS_MESSAGE);
        }
        if (userRepository.findByUsername(userModel.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", Constants.USERNAME_ALREADY_EXISTS_MESSAGE);
        }
        return !bindingResult.hasErrors();
    }

    private void registerAccountIntern(UserRegisterBindingModel userModel) {
        User user = this.modelMapper.map(userModel, User.class);
        user.setRole(this.generateUserRole());
        user.setPassword(encoder.encode(userModel.getPassword()));
        this.userRepository.save(user);
    }

    private Role generateUserRole() {
        if (this.userRepository.count() == 0) {
            return this.roleService.findRoleByRoleName(RoleType.ADMIN.name());
        }
        return this.roleService.findRoleByRoleName(RoleType.USER.name());
    }
}

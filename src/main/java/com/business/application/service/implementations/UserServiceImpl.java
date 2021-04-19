package com.business.application.service.implementations;

import com.business.application.constants.Constants;
import com.business.application.entity.Role;
import com.business.application.entity.User;
import com.business.application.entity.binding.UserRegisterBindingModel;
import com.business.application.entity.view.UserViewModel;
import com.business.application.enumerations.RoleType;
import com.business.application.exceptions.CreateAccountException;
import com.business.application.repository.UserRepository;
import com.business.application.service.UserService;
import com.business.application.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder encoder) {
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
            User currentUser = this.userRepository.findByEmail(email).orElse(null);
            if (currentUser != null) {
                currentUser.setAuthorities(Collections.emptyList());
                this.userRepository.saveAndFlush(currentUser);
                this.userRepository.deleteByEmail(email);
            }
        } catch (Exception e) {
            throw new CreateAccountException();
        }
    }

    @Override
    public User findUserById(String id){
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void saveAndFlushUser(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void doDeleteAccountByEmployeeId(String id) {
        this.userRepository.deleteByEmployeeId(id);
    }

    @Override
    public List<UserViewModel> findAllUsers() {
        return this.userRepository
                .findAll()
                .stream()
                .map(user -> this.modelMapper.map(user, UserViewModel.class))
                .collect(Collectors.toList());
    }

    private Boolean validateRegistrationInput(UserRegisterBindingModel userModel, BindingResult bindingResult) {
        if (userModel.getPassword() == null || userModel.getRepeatPassword() == null ||
                !userModel.getPassword().equals(userModel.getRepeatPassword())) {
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
        user.setAuthorities(List.of(this.generateUserRole()));
        user.setPassword(encoder.encode(userModel.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setErrorCount(0);
        this.userRepository.save(user);
    }

    private Role generateUserRole() {
        if (this.userRepository.count() == 0) {
            this.roleService.saveAndFlushRoles();
            return this.roleService.findRoleByRoleName(RoleType.ROLE_ADMIN);
        }
        return this.roleService.findRoleByRoleName(RoleType.ROLE_USER);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }
}

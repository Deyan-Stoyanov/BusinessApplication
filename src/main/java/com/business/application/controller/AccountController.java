package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.binding.UserRegisterBindingModel;
import com.business.application.exceptions.CreateAccountException;
import com.business.application.service.implementations.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class AccountController {

    private static final String REGISTER_VIEW_NAME = "register";
    private static final String LOGIN_VIEW_NAME = "login";
    private static final String REGISTER_ENDPOINT_NAME = "/register";
    private static final String LOGIN_ENDPOINT_NAME = "/login";

    private final UserServiceImpl userService;

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ANONYMOUS)
    @GetMapping(LOGIN_ENDPOINT_NAME)
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName(LOGIN_VIEW_NAME);

        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ANONYMOUS)
    @GetMapping(REGISTER_ENDPOINT_NAME)
    public ModelAndView doGetRegister(ModelAndView modelAndView, @ModelAttribute(name = "user") UserRegisterBindingModel userModel) {
        modelAndView.setViewName(REGISTER_VIEW_NAME);

        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ANONYMOUS)
    @PostMapping(REGISTER_ENDPOINT_NAME)
    public ModelAndView doPostRegister(@Valid @ModelAttribute(name = "user") UserRegisterBindingModel userModel,
                                       BindingResult bindingResult,
                                       ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(REGISTER_VIEW_NAME);
            return modelAndView;
        }
        try {
            this.userService.doRegisterAccount(userModel, bindingResult);

            if (bindingResult.hasErrors()) {
                modelAndView.setViewName(REGISTER_VIEW_NAME);
            } else {
                modelAndView.setViewName(LOGIN_VIEW_NAME);
            }
        } catch (CreateAccountException e) {
            logger.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
            modelAndView.setViewName(Constants.ERROR_VIEW_NAME);
        }

        return modelAndView;
    }
}

package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.binding.UserRegisterBindingModel;
import com.business.application.service.implementations.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AccountController {

    private static final String REGISTER_VIEW_NAME = "register";
    private static final String LOGIN_VIEW_NAME = "login";
    private static final String REGISTER_ENDPOINT_NAME = "/register";
    private static final String LOGIN_ENDPOINT_NAME = "/login";

    private final AccountServiceImpl accountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ANONYMOUS)
    @GetMapping(LOGIN_ENDPOINT_NAME)
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName(LOGIN_VIEW_NAME);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ANONYMOUS)
    @GetMapping(REGISTER_ENDPOINT_NAME)
    public ModelAndView doGetRegister(ModelAndView modelAndView) {
        modelAndView.setViewName(REGISTER_VIEW_NAME);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ANONYMOUS)
    @PostMapping(REGISTER_ENDPOINT_NAME)
    public ModelAndView doPostRegister(@Valid @ModelAttribute(name = "user") UserRegisterBindingModel userModel,
                                       BindingResult bindingResult, ModelAndView modelAndView) {
        this.accountService.doRegisterAccount(userModel, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(REGISTER_VIEW_NAME);
        } else {
            modelAndView.setViewName(LOGIN_VIEW_NAME);
        }
        return modelAndView;
    }
}
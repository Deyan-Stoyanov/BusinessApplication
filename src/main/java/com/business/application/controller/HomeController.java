package com.business.application.controller;

import com.business.application.constants.Constants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ANONYMOUS)
    @GetMapping("/")
    public ModelAndView start(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_AUTHENTICATED)
    @GetMapping("/home")
    public ModelAndView homePage(ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        return modelAndView;
    }

}

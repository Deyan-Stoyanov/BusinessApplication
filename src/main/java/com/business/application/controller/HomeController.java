package com.business.application.controller;

import com.business.application.constants.Constants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private static final String START_PAGE_ENDPOINT_NAME = "/";
    private static final String HOME_ENDPOINT_NAME = "/home";
    private static final String INDEX_VIEW_NAME = "index";
    private static final String HOME_VIEW_NAME = "home";

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ANONYMOUS)
    @GetMapping(START_PAGE_ENDPOINT_NAME)
    public ModelAndView start(ModelAndView modelAndView) {
        modelAndView.setViewName(INDEX_VIEW_NAME);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_AUTHENTICATED)
    @GetMapping(HOME_ENDPOINT_NAME)
    public ModelAndView homePage(ModelAndView modelAndView) {
        modelAndView.setViewName(HOME_VIEW_NAME);
        return modelAndView;
    }

}

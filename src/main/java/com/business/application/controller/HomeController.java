package com.business.application.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class HomeController {
    @PreAuthorize("isAnonymous()")
    @GetMapping("/")
    public ModelAndView start(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }
}

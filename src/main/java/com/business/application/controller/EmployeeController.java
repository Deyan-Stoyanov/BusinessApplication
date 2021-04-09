package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.Employee;
import com.business.application.entity.binding.EmployeeBindingModel;
import com.business.application.exceptions.CreateOrUpdateEmployeeException;
import com.business.application.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class EmployeeController {

    private static final String PERSONAL_DATA_ENDPOINT_NAME = "/personalData/{id}";
    private static final String PERSONAL_DATA_VIEW_NAME = "personalData";

    private final EmployeeService employeeService;
    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_AUTHENTICATED)
    @GetMapping(PERSONAL_DATA_ENDPOINT_NAME)
    public ModelAndView personalData(@PathVariable("id") String id,
                                     ModelAndView modelAndView,
                                     @ModelAttribute(name = "employee") EmployeeBindingModel employee) {
        modelAndView.setViewName(PERSONAL_DATA_VIEW_NAME);
        Employee currentEmployee = this.employeeService.findEmployeeByUserId(id);
        modelAndView.addObject("employee", currentEmployee == null ? new EmployeeBindingModel() : currentEmployee);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_AUTHENTICATED)
    @PostMapping(PERSONAL_DATA_ENDPOINT_NAME)
    public ModelAndView doPostRegisterEmployee(@PathVariable("id") String id,
                                               @Valid @ModelAttribute(name = "employee") EmployeeBindingModel employeeModel,
                                               BindingResult bindingResult,
                                               ModelAndView modelAndView) {
        try {
            this.employeeService.doRegisterOrUpdateEmployee(employeeModel, id, bindingResult);
            modelAndView.setViewName(PERSONAL_DATA_VIEW_NAME);
        } catch (CreateOrUpdateEmployeeException e) {
            logger.error(e.getMessage(), e);
            modelAndView.setViewName(Constants.ERROR_VIEW_NAME);
        }

        return modelAndView;
    }
}

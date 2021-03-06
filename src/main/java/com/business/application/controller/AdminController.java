package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.binding.*;
import com.business.application.entity.view.*;
import com.business.application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.business.application.constants.Constants.ADMIN_URL;
import static com.business.application.constants.Constants.REDIRECT_URL;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final String EMPLOYEES_ENDPOINT_NAME = "/employees";
    private static final String EMPLOYEES_VIEW_NAME = "employees";

    private final EmployeeService employeeService;
    private final UserService userService;

    @Autowired
    public AdminController(EmployeeService employeeService, UserService userService) {
        this.employeeService = employeeService;
        this.userService = userService;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(EMPLOYEES_ENDPOINT_NAME)
    public ModelAndView employees(ModelAndView modelAndView) {
        modelAndView.setViewName(EMPLOYEES_VIEW_NAME);
        List<UserViewModel> users = userService.findAllUsers();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @PatchMapping(EMPLOYEES_ENDPOINT_NAME + "/{id}")
    public ModelAndView editEmployee(@PathVariable("id") String id,
                                     @Valid @ModelAttribute(name = "employee") EmployeeBindingModel employeeModel,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView) {
        employeeService.editEmployee(id, employeeModel, bindingResult);
        modelAndView.setViewName(REDIRECT_URL + ADMIN_URL + EMPLOYEES_VIEW_NAME);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(EMPLOYEES_ENDPOINT_NAME + "/delete/{id}")
    public ModelAndView deleteEmployeeAndUser(@PathVariable("id") String id,
                                              ModelAndView modelAndView) {
        EmployeeViewModel employee = employeeService.findEmployeeByUserId(id);
        if(employee != null){
            employeeService.deleteEmployee(employee.getId());
        } else {
            userService.deleteById(id);
        }
        modelAndView.setViewName(REDIRECT_URL + "/admin" + EMPLOYEES_ENDPOINT_NAME);
        return modelAndView;
    }
}

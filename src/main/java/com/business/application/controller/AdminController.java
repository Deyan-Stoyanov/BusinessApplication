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
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final String EMPLOYEES_ENDPOINT_NAME = "/employees";
    private static final String EMPLOYEES_VIEW_NAME = "employees";
    private static final String ELEMENTS_ENDPOINT_NAME = "/elements";
    private static final String ELEMENTS_VIEW_NAME = "elements";
    private static final String MACHINES_ENDPOINT_NAME = "/machines";
    private static final String MACHINES_VIEW_NAME = "machines";
    private static final String ALLOYS_ENDPOINT_NAME = "/alloys";
    private static final String ALLOYS_VIEW_NAME = "alloys";
    private static final String SHIFTS_ENDPOINT_NAME = "/shifts";
    private static final String SHIFTS_VIEW_NAME = "shifts";
    private static final String REDIRECT_URL = "redirect:";

    private final EmployeeService employeeService;
    private final UserService userService;
    private final MachineService machineService;
    private final AlloyService alloyService;
    private final ElementService elementService;
    private final ShiftService shiftService;

    @Autowired
    public AdminController(EmployeeService employeeService, UserService userService, MachineService machineService, AlloyService alloyService, ElementService elementService, ShiftService shiftService) {
        this.employeeService = employeeService;
        this.userService = userService;
        this.machineService = machineService;
        this.alloyService = alloyService;
        this.elementService = elementService;
        this.shiftService = shiftService;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(EMPLOYEES_ENDPOINT_NAME)
    public ModelAndView employees(ModelAndView modelAndView) {
        modelAndView.setViewName(EMPLOYEES_VIEW_NAME);
        List<EmployeeViewModel> employees = employeeService.findAllEmployees();
        List<UserViewModel> users = userService.findAllUsers();
        modelAndView.addObject("employees", employees);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @PatchMapping(EMPLOYEES_ENDPOINT_NAME)
    public ModelAndView editEmployee(@PathVariable("id") String id,
                                     @Valid @ModelAttribute(name = "employee") EmployeeBindingModel employeeModel,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView) {
        employeeService.editEmployee(id, employeeModel, bindingResult);
        modelAndView.setViewName(REDIRECT_URL + EMPLOYEES_VIEW_NAME);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @DeleteMapping(EMPLOYEES_ENDPOINT_NAME)
    public ModelAndView deleteEmployeeAndUser(@PathVariable("id") String id,
                                              ModelAndView modelAndView) {
        employeeService.deleteEmployee(id);
        modelAndView.setViewName(REDIRECT_URL + EMPLOYEES_ENDPOINT_NAME);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(MACHINES_ENDPOINT_NAME)
    public ModelAndView machines(ModelAndView modelAndView) {
        modelAndView.setViewName(MACHINES_VIEW_NAME);
        List<MachineViewModel> machines = machineService.getAllMachines();
        modelAndView.addObject("machines", machines);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @PostMapping(MACHINES_ENDPOINT_NAME)
    public ModelAndView doRegisterMachine(@Valid @ModelAttribute(name = "machine") MachineBindingModel machineBindingModel,
                                          BindingResult bindingResult,
                                          ModelAndView modelAndView) {
        modelAndView.setViewName(REDIRECT_URL + MACHINES_ENDPOINT_NAME);
        machineService.addNewMachine(machineBindingModel, bindingResult);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(ALLOYS_ENDPOINT_NAME)
    public ModelAndView alloys(ModelAndView modelAndView) {
        modelAndView.setViewName(ALLOYS_VIEW_NAME);
        List<AlloyViewModel> alloys = alloyService.getAllAlloys();
        modelAndView.addObject("alloys", alloys);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @PostMapping(ALLOYS_ENDPOINT_NAME)
    public ModelAndView doRegisterAlloy(@Valid @ModelAttribute(name = "alloy") AlloyBindingModel alloyBindingModel,
                                          BindingResult bindingResult,
                                          ModelAndView modelAndView) {
        modelAndView.setViewName(REDIRECT_URL + ALLOYS_ENDPOINT_NAME);
        alloyService.addNewAlloy(alloyBindingModel, bindingResult);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(ELEMENTS_ENDPOINT_NAME)
    public ModelAndView elements(ModelAndView modelAndView) {
        modelAndView.setViewName(ELEMENTS_VIEW_NAME);
        List<ElementViewModel> elements = elementService.getAllElements();
        modelAndView.addObject("elements", elements);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @PostMapping(ELEMENTS_ENDPOINT_NAME)
    public ModelAndView doRegisterElement(@Valid @ModelAttribute(name = "element") ElementBindingModel elementBindingModel,
                                          BindingResult bindingResult,
                                          ModelAndView modelAndView) {
        modelAndView.setViewName(REDIRECT_URL + ELEMENTS_ENDPOINT_NAME);
        elementService.addNewElement(elementBindingModel, bindingResult);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(SHIFTS_ENDPOINT_NAME)
    public ModelAndView shifts(ModelAndView modelAndView) {
        modelAndView.setViewName(SHIFTS_VIEW_NAME);
        List<ShiftViewModel> elements = shiftService.findAllShifts();
        modelAndView.addObject("elements", elements);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @PostMapping(SHIFTS_ENDPOINT_NAME)
    public ModelAndView doRegisterShift(@Valid @ModelAttribute(name = "shift") ShiftBindingModel shiftBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView) {
        modelAndView.setViewName(REDIRECT_URL + SHIFTS_ENDPOINT_NAME);
        shiftService.addNewShift(shiftBindingModel, bindingResult);
        return modelAndView;
    }
}

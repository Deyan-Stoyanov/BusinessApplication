package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.binding.MachineBindingModel;
import com.business.application.entity.view.MachineViewModel;
import com.business.application.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static com.business.application.constants.Constants.ADMIN_URL;
import static com.business.application.constants.Constants.ID_URL_SUFFIX;

@Controller
@RequestMapping("/admin")
public class MachineController {

    private static final String MACHINES_ENDPOINT_NAME = "/machines";
    private static final String MACHINES_DELETE_ENDPOINT_NAME = "/machines/delete";
    private static final String MACHINES_VIEW_NAME = "machines";

    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
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
        modelAndView.setViewName(Constants.REDIRECT_URL + ADMIN_URL + MACHINES_ENDPOINT_NAME);
        machineService.addNewMachine(machineBindingModel, bindingResult);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(MACHINES_DELETE_ENDPOINT_NAME + ID_URL_SUFFIX)
    public ModelAndView doDeleteMachine(@PathVariable("id") String id,
                                          ModelAndView modelAndView) {
        modelAndView.setViewName(Constants.REDIRECT_URL + ADMIN_URL + MACHINES_ENDPOINT_NAME);
        machineService.deleteById(id);
        return modelAndView;
    }
}

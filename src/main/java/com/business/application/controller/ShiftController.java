package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.binding.ShiftBindingModel;
import com.business.application.entity.binding.ShiftSearchBindingModel;
import com.business.application.entity.view.ShiftViewModel;
import com.business.application.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class ShiftController {

    private static final String MY_SHIFTS_ENDPOINT_NAME = "/shifts/{id}";
    private static final String MY_SHIFTS_VIEW_NAME = "myShifts";
    private static final String RECORD_SHIFT_ENDPOINT_NAME = "/recordShift";
    private static final String RECORD_SHIFT_VIEW_NAME = "recordShift";
    private static final String SHIFTS_ENDPOINT_NAME = "/admin/shifts";
    private static final String SHIFTS_VIEW_NAME = "shifts";

    private final ShiftService shiftService;
    private final MachineService machineService;
    private final ElementService elementService;
    private final AlloyService alloyService;

    @Autowired
    public ShiftController(ShiftService shiftService, MachineService machineService, ElementService elementService, AlloyService alloyService) {
        this.shiftService = shiftService;
        this.machineService = machineService;
        this.elementService = elementService;
        this.alloyService = alloyService;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_AUTHENTICATED)
    @GetMapping(MY_SHIFTS_ENDPOINT_NAME)
    public ModelAndView myShifts(@PathVariable("id") String id,
                                     ModelAndView modelAndView) {
        modelAndView.setViewName(MY_SHIFTS_VIEW_NAME);
        List<ShiftViewModel> shifts = this.shiftService.findShiftsByUserId(id);
        modelAndView.addObject("shifts", shifts == null ? Collections.emptyList() : shifts);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_AUTHENTICATED)
    @GetMapping(RECORD_SHIFT_ENDPOINT_NAME)
    public ModelAndView getRecordNewShift(ModelAndView modelAndView) {
        modelAndView.setViewName(RECORD_SHIFT_VIEW_NAME);
        modelAndView.addObject("machines", machineService.getAllMachines());
        modelAndView.addObject("elements", elementService.getAllElements());
        modelAndView.addObject("alloys", alloyService.getAllAlloys());
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_AUTHENTICATED)
    @PostMapping(RECORD_SHIFT_ENDPOINT_NAME + "/{id}")
    public ModelAndView doRecordNewShift(@PathVariable("id") String id,
                                 @Valid @ModelAttribute(name = "shift") ShiftBindingModel shiftModel,
                                 BindingResult bindingResult,
                                 ModelAndView modelAndView) {
        modelAndView.setViewName(RECORD_SHIFT_VIEW_NAME);
        this.shiftService.recordNewShift(shiftModel, id);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(SHIFTS_ENDPOINT_NAME)
    public ModelAndView shifts(ModelAndView modelAndView) {
        modelAndView.setViewName(SHIFTS_VIEW_NAME);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @PostMapping(SHIFTS_ENDPOINT_NAME)
    public ModelAndView postShifts(@ModelAttribute(name = "shiftSearchModel") ShiftSearchBindingModel shiftSearchBindingModel,
                                   ModelAndView modelAndView) {
        modelAndView.setViewName(SHIFTS_VIEW_NAME);
        List<ShiftViewModel> shifts = shiftService.findAllShiftsByDate(shiftSearchBindingModel.getStartDate(), shiftSearchBindingModel.getEndDate());
        modelAndView.addObject("shifts", shifts);
        return modelAndView;
    }
}

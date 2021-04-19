package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.Shift;
import com.business.application.entity.binding.ShiftBindingModel;
import com.business.application.entity.view.ShiftViewModel;
import com.business.application.service.ShiftService;
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
    private static final String RECORD_SHIFT_ENDPOINT_NAME = "/recordShift/{id}";
    private static final String RECORD_SHIFT_VIEW_NAME = "recordShift";
    private static final String SHIFTS_ENDPOINT_NAME = "/admin/shifts";
    private static final String SHIFTS_VIEW_NAME = "shifts";

    private final ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_AUTHENTICATED)
    @GetMapping(MY_SHIFTS_ENDPOINT_NAME)
    public ModelAndView myShifts(@PathVariable("id") String id,
                                     ModelAndView modelAndView) {
        modelAndView.setViewName(MY_SHIFTS_VIEW_NAME);
        List<Shift> shifts = this.shiftService.findShiftsByEmployeeId(id);
        modelAndView.addObject("shifts", shifts == null ? Collections.emptyList() : shifts);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_AUTHENTICATED)
    @GetMapping(RECORD_SHIFT_ENDPOINT_NAME)
    public ModelAndView recordNewShift(ModelAndView modelAndView) {
        modelAndView.setViewName(RECORD_SHIFT_VIEW_NAME);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_AUTHENTICATED)
    @PostMapping(RECORD_SHIFT_ENDPOINT_NAME)
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
        List<ShiftViewModel> elements = shiftService.findAllShifts();
        modelAndView.addObject("elements", elements);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @PostMapping(SHIFTS_ENDPOINT_NAME)
    public ModelAndView doRegisterShift(@Valid @ModelAttribute(name = "shift") ShiftBindingModel shiftBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView) {
        modelAndView.setViewName(Constants.REDIRECT_URL + SHIFTS_ENDPOINT_NAME);
        shiftService.addNewShift(shiftBindingModel, bindingResult);
        return modelAndView;
    }
}

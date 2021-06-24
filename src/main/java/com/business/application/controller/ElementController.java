package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.binding.ElementBindingModel;
import com.business.application.entity.view.AlloyViewModel;
import com.business.application.entity.view.ElementViewModel;
import com.business.application.entity.view.MachineViewModel;
import com.business.application.service.AlloyService;
import com.business.application.service.ElementService;
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
public class ElementController {

    private static final String ELEMENTS_ENDPOINT_NAME = "/elements";
    private static final String ELEMENTS_VIEW_NAME = "elements";
    private static final String ELEMENTS_DELETE_ENDPOINT_NAME = "/elements/delete";

    private final ElementService elementService;
    private final AlloyService alloyService;
    private final MachineService machineService;

    @Autowired
    public ElementController(ElementService elementService, AlloyService alloyService, MachineService machineService) {
        this.elementService = elementService;
        this.alloyService = alloyService;
        this.machineService = machineService;
    }


    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(ELEMENTS_ENDPOINT_NAME)
    public ModelAndView elements(ModelAndView modelAndView) {
        modelAndView.setViewName(ELEMENTS_VIEW_NAME);
        List<ElementViewModel> elements = elementService.getAllElements();
        modelAndView.addObject("elements", elements);
        List<AlloyViewModel> alloys = this.alloyService.getAllAlloys();
        modelAndView.addObject("alloys", alloys);
        List<MachineViewModel> machines = this.machineService.getAllMachines();
        modelAndView.addObject("machines", machines);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @PostMapping(ELEMENTS_ENDPOINT_NAME)
    public ModelAndView doRegisterElement(@Valid @ModelAttribute(name = "element") ElementBindingModel elementBindingModel,
                                          BindingResult bindingResult,
                                          ModelAndView modelAndView) {
        modelAndView.setViewName(Constants.REDIRECT_URL + ADMIN_URL + ELEMENTS_ENDPOINT_NAME);
        elementService.addNewElement(elementBindingModel, bindingResult);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(ELEMENTS_DELETE_ENDPOINT_NAME + ID_URL_SUFFIX)
    public ModelAndView doDeleteElement(@PathVariable("id") String id,
                                        ModelAndView modelAndView) {
        modelAndView.setViewName(Constants.REDIRECT_URL + ADMIN_URL + ELEMENTS_ENDPOINT_NAME);
        elementService.deleteById(id);
        return modelAndView;
    }
}

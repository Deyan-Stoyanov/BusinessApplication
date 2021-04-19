package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.binding.AlloyBindingModel;
import com.business.application.entity.view.AlloyViewModel;
import com.business.application.service.AlloyService;
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
public class AlloyController {

    private static final String ALLOYS_ENDPOINT_NAME = "/alloys";
    private static final String ALLOYS_DELETE_ENDPOINT_NAME = "/alloys/delete";
    private static final String ALLOYS_VIEW_NAME = "alloys";

    private final AlloyService alloyService;

    @Autowired
    public AlloyController(AlloyService alloyService) {
        this.alloyService = alloyService;
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
        modelAndView.setViewName(Constants.REDIRECT_URL + ADMIN_URL + ALLOYS_ENDPOINT_NAME);
        alloyService.addNewAlloy(alloyBindingModel, bindingResult);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(ALLOYS_DELETE_ENDPOINT_NAME + ID_URL_SUFFIX)
    public ModelAndView doDeleteAlloy(@PathVariable("id") String id,
                                      ModelAndView modelAndView) {
        modelAndView.setViewName(Constants.REDIRECT_URL + ADMIN_URL + ALLOYS_ENDPOINT_NAME);
        alloyService.deleteById(id);
        return modelAndView;
    }
}

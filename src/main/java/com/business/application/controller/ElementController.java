package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.binding.ElementBindingModel;
import com.business.application.entity.view.ElementViewModel;
import com.business.application.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static com.business.application.constants.Constants.ADMIN_URL;

@Controller
@RequestMapping("/admin")
public class ElementController {

    private static final String ELEMENTS_ENDPOINT_NAME = "/elements";
    private static final String ELEMENTS_VIEW_NAME = "elements";

    private final ElementService elementService;

    @Autowired
    public ElementController(ElementService elementService) {
        this.elementService = elementService;
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
        modelAndView.setViewName(Constants.REDIRECT_URL + ADMIN_URL + ELEMENTS_ENDPOINT_NAME);
        elementService.addNewElement(elementBindingModel, bindingResult);
        return modelAndView;
    }
}

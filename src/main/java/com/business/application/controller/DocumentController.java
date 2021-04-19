package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.binding.DocumentBindingModel;
import com.business.application.entity.view.DocumentViewModel;
import com.business.application.service.DocumentService;
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
import static com.business.application.constants.Constants.REDIRECT_URL;

@Controller
@RequestMapping("/admin")
public class DocumentController {
    private static final String DOCUMENTS_ENDPOINT_NAME = "/documents";
    private static final String DOCUMENTS_VIEW_NAME = "documents";

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(DOCUMENTS_ENDPOINT_NAME)
    public ModelAndView documents(ModelAndView modelAndView) {
        modelAndView.setViewName(DOCUMENTS_VIEW_NAME);
        List<DocumentViewModel> documents = documentService.getAllDocuments();
        modelAndView.addObject("documents", documents);
        return modelAndView;
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @PostMapping(DOCUMENTS_ENDPOINT_NAME)
    public ModelAndView doAddDocument(@Valid @ModelAttribute(name = "document") DocumentBindingModel documentBindingModel,
                                          BindingResult bindingResult,
                                          ModelAndView modelAndView) {
        modelAndView.setViewName(REDIRECT_URL + ADMIN_URL + DOCUMENTS_ENDPOINT_NAME);
        documentService.addNewDocument(documentBindingModel, bindingResult);
        return modelAndView;
    }
}

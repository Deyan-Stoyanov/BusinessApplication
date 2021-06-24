package com.business.application.controller;

import com.business.application.constants.Constants;
import com.business.application.entity.Document;
import com.business.application.entity.binding.DocumentBindingModel;
import com.business.application.entity.view.DocumentViewModel;
import com.business.application.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(DOCUMENTS_ENDPOINT_NAME + "/{id}")
    public void downloadDocument(@PathVariable String id, HttpServletResponse resp) throws IOException {
        DocumentViewModel dbFile = documentService.findDocumentById(id);
        byte[] byteArray =  dbFile.getDocumentContent();
        resp.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM.getType());
        resp.setHeader("Content-Disposition", "attachment; filename=" + dbFile.getDocumentName() + ".txt");
        resp.setContentLength(byteArray.length);
        try (OutputStream os = resp.getOutputStream()) {
            os.write(byteArray, 0, byteArray.length);
        }
    }

    @PreAuthorize(Constants.PRE_AUTHORIZATION_CONDITION_ADMIN)
    @GetMapping(DOCUMENTS_ENDPOINT_NAME + "/delete/{id}")
    public ModelAndView deleteDocument(@PathVariable String id,
                               ModelAndView modelAndView) {
        modelAndView.setViewName(REDIRECT_URL + ADMIN_URL + DOCUMENTS_ENDPOINT_NAME);
        documentService.deleteDocumentById(id);
        return modelAndView;
    }
}

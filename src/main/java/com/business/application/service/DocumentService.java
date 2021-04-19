package com.business.application.service;

import com.business.application.entity.binding.DocumentBindingModel;
import com.business.application.entity.view.DocumentViewModel;
import com.business.application.entity.view.MachineViewModel;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface DocumentService {

    List<DocumentViewModel> getAllDocuments();

    void addNewDocument(DocumentBindingModel documentBindingModel, BindingResult bindingResult);

}

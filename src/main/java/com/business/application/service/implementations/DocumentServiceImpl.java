package com.business.application.service.implementations;

import com.business.application.entity.binding.DocumentBindingModel;
import com.business.application.entity.view.DocumentViewModel;
import com.business.application.repository.DocumentRepository;
import com.business.application.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public List<DocumentViewModel> getAllDocuments() {
        return null;
    }

    @Override
    public void addNewDocument(DocumentBindingModel documentBindingModel, BindingResult bindingResult) {

    }
}

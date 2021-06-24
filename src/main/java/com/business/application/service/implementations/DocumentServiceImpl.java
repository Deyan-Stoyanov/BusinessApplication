package com.business.application.service.implementations;

import com.business.application.entity.Document;
import com.business.application.entity.binding.DocumentBindingModel;
import com.business.application.entity.view.DocumentViewModel;
import com.business.application.repository.DocumentRepository;
import com.business.application.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentViewModel findDocumentById(String id) {
        Document document = this.documentRepository.findById(id).orElse(null);
        return document == null ? null : this.modelMapper.map(document, DocumentViewModel.class);
    }

    @Override
    public List<DocumentViewModel> getAllDocuments() {
        return this.documentRepository.findAll()
                .stream()
                .map(document -> this.modelMapper.map(document, DocumentViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewDocument(DocumentBindingModel documentBindingModel, BindingResult bindingResult) {
        Document document = this.modelMapper.map(documentBindingModel, Document.class);
        this.documentRepository.saveAndFlush(document);
    }

    @Override
    public void deleteDocumentById(String id) {
        this.documentRepository.deleteById(id);
    }
}

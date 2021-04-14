package com.business.application.service.implementations;

import com.business.application.entity.binding.ElementBindingModel;
import com.business.application.entity.view.ElementViewModel;
import com.business.application.repository.ElementRepository;
import com.business.application.service.ElementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElementServiceImpl implements ElementService {

    private final ElementRepository elementRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ElementServiceImpl(ElementRepository elementRepository, ModelMapper modelMapper) {
        this.elementRepository = elementRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ElementViewModel> getAllElements() {
        return this.elementRepository
                .findAll()
                .stream()
                .map(element -> this.modelMapper.map(element, ElementViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewElement(ElementBindingModel elementBindingModel, BindingResult bindingResult) {

    }
}

package com.business.application.service;

import com.business.application.entity.binding.ElementBindingModel;
import com.business.application.entity.view.ElementViewModel;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ElementService {

    List<ElementViewModel> getAllElements();

    void addNewElement(ElementBindingModel elementBindingModel, BindingResult bindingResult);

    void deleteById(String id);
}

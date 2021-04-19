package com.business.application.service;

import com.business.application.entity.Alloy;
import com.business.application.entity.binding.AlloyBindingModel;
import com.business.application.entity.view.AlloyViewModel;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface AlloyService {

    List<AlloyViewModel> getAllAlloys();

    Alloy findByAlloyNumber(String alloyNumber);

    void addNewAlloy(AlloyBindingModel alloyBindingModel, BindingResult bindingResult);

    void deleteById(String id);

}

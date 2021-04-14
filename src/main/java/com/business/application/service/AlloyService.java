package com.business.application.service;

import com.business.application.entity.binding.AlloyBindingModel;
import com.business.application.entity.view.AlloyViewModel;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface AlloyService {

    List<AlloyViewModel> getAllAlloys();

    void addNewAlloy(AlloyBindingModel alloyBindingModel, BindingResult bindingResult);

}

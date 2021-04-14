package com.business.application.service.implementations;

import com.business.application.entity.binding.AlloyBindingModel;
import com.business.application.entity.view.AlloyViewModel;
import com.business.application.repository.AlloyRepository;
import com.business.application.service.AlloyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlloyServiceImpl implements AlloyService {

    private final AlloyRepository alloyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AlloyServiceImpl(AlloyRepository alloyRepository, ModelMapper modelMapper) {
        this.alloyRepository = alloyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AlloyViewModel> getAllAlloys() {
        return this.alloyRepository
                .findAll()
                .stream()
                .map(alloy -> this.modelMapper.map(alloy, AlloyViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewAlloy(AlloyBindingModel alloyBindingModel, BindingResult bindingResult) {
        
    }
}

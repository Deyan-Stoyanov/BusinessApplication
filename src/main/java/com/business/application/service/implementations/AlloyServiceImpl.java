package com.business.application.service.implementations;

import com.business.application.entity.Alloy;
import com.business.application.entity.binding.AlloyBindingModel;
import com.business.application.entity.view.AlloyViewModel;
import com.business.application.entity.view.ElementViewModel;
import com.business.application.repository.AlloyRepository;
import com.business.application.service.AlloyService;
import com.business.application.service.ElementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
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
                .sorted(Comparator.comparing(AlloyViewModel::getAlloyNumber))
                .collect(Collectors.toList());
    }

    @Override
    public Alloy findByAlloyNumber(String alloyNumber){
        return this.alloyRepository.findByAlloyNumber(alloyNumber).orElse(null);
    }

    @Override
    public Alloy findById(String id){
        return this.alloyRepository.findById(id).orElse(null);
    }

    @Override
    public void addNewAlloy(AlloyBindingModel alloyBindingModel, BindingResult bindingResult) {
        Alloy alloy = this.modelMapper.map(alloyBindingModel, Alloy.class);
        alloy.setWeightPerUnit(alloy.getWeight().divide(BigDecimal.valueOf(alloy.getUnits()), 10, RoundingMode.HALF_UP));
        this.alloyRepository.saveAndFlush(alloy);
    }

    @Override
    public void deleteById(String id) {
        this.alloyRepository.deleteById(id);
    }
}

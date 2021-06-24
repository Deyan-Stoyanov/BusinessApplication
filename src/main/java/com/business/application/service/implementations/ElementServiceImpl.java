package com.business.application.service.implementations;

import com.business.application.entity.Alloy;
import com.business.application.entity.Element;
import com.business.application.entity.Machine;
import com.business.application.entity.binding.ElementBindingModel;
import com.business.application.entity.view.ElementViewModel;
import com.business.application.entity.view.ShiftViewModel;
import com.business.application.repository.ElementRepository;
import com.business.application.service.AlloyService;
import com.business.application.service.ElementService;
import com.business.application.service.MachineService;
import com.business.application.service.ShiftService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElementServiceImpl implements ElementService {

    private final ElementRepository elementRepository;
    private final AlloyService alloyService;
    private final MachineService machineService;
    private final ShiftService shiftService;
    private final ModelMapper modelMapper;

    @Autowired
    public ElementServiceImpl(ElementRepository elementRepository, AlloyService alloyService, MachineService machineService, ShiftService shiftService, ModelMapper modelMapper) {
        this.elementRepository = elementRepository;
        this.alloyService = alloyService;
        this.machineService = machineService;
        this.shiftService = shiftService;
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
        Element element = this.modelMapper.map(elementBindingModel, Element.class);
        Alloy alloy = this.alloyService.findById(elementBindingModel.getAlloy());
        Machine machine = this.machineService.findById(elementBindingModel.getMachine());
        element.setAlloy(alloy);
        element.setMachine(machine);
        this.elementRepository.saveAndFlush(element);
    }

    @Override
    public void deleteById(String id) {
        this.elementRepository.deleteById(id);
    }
}

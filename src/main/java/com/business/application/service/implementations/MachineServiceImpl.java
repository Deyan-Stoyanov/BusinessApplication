package com.business.application.service.implementations;

import com.business.application.entity.Machine;
import com.business.application.entity.binding.MachineBindingModel;
import com.business.application.entity.view.MachineViewModel;
import com.business.application.repository.MachineRepository;
import com.business.application.service.MachineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MachineServiceImpl(MachineRepository machineRepository, ModelMapper modelMapper) {
        this.machineRepository = machineRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MachineViewModel> getAllMachines() {
        return this.machineRepository.findAll()
                .stream()
                .map(machine -> this.modelMapper.map(machine, MachineViewModel.class))
                .sorted(Comparator.comparing(MachineViewModel::getMachineName))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewMachine(MachineBindingModel machineBindingModel, BindingResult bindingResult) {

    }
}

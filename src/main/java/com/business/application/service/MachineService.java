package com.business.application.service;

import com.business.application.entity.binding.MachineBindingModel;
import com.business.application.entity.view.MachineViewModel;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface MachineService {
    List<MachineViewModel> getAllMachines();

    void addNewMachine(MachineBindingModel machineBindingModel, BindingResult bindingResult);
}

package com.business.application.service.implementations;

import com.business.application.entity.Shift;
import com.business.application.entity.binding.ShiftBindingModel;
import com.business.application.entity.view.ShiftViewModel;
import com.business.application.repository.ShiftRepository;
import com.business.application.service.ShiftService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShiftServiceImpl(ShiftRepository shiftRepository, ModelMapper modelMapper) {
        this.shiftRepository = shiftRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Shift> findShiftsByEmployeeId(String employeeId){
        return this.shiftRepository.findAllByEmployeeId(employeeId);
    }

    @Override
    public void recordNewShift(ShiftBindingModel shiftModel, String id) {

    }

    @Override
    public List<ShiftViewModel> findAllShifts() {
        return this.shiftRepository
                .findAll()
                .stream()
                .map(shift -> this.modelMapper.map(shift, ShiftViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewShift(ShiftBindingModel shiftBindingModel, BindingResult bindingResult) {

    }
}

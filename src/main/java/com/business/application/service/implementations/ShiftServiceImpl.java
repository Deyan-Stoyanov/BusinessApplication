package com.business.application.service.implementations;

import com.business.application.entity.Alloy;
import com.business.application.entity.Employee;
import com.business.application.entity.Shift;
import com.business.application.entity.binding.ShiftBindingModel;
import com.business.application.entity.view.ShiftViewModel;
import com.business.application.repository.*;
import com.business.application.service.ShiftService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;
    private final MachineRepository machineRepository;
    private final AlloyRepository alloyRepository;
    private final ElementRepository elementRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShiftServiceImpl(ShiftRepository shiftRepository, EmployeeRepository employeeRepository, MachineRepository machineRepository, AlloyRepository alloyRepository, ElementRepository elementRepository, ModelMapper modelMapper) {
        this.shiftRepository = shiftRepository;
        this.employeeRepository = employeeRepository;
        this.machineRepository = machineRepository;
        this.alloyRepository = alloyRepository;
        this.elementRepository = elementRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ShiftViewModel> findShiftsByUserId(String userId) {
        Employee employee = this.employeeRepository.findByUserId(userId).orElse(null);
        if(employee == null){
           throw new IllegalArgumentException("Employee not found");
        }
        String employeeId = employee.getId();
        return this.shiftRepository.findAllByEmployeeId(employeeId)
                .stream()
                .map(shift -> this.modelMapper.map(shift, ShiftViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void recordNewShift(ShiftBindingModel shiftModel, String id) {
        Shift shift = this.modelMapper.map(shiftModel, Shift.class);
        shift.setEmployee(this.employeeRepository.findByUserId(id).orElse(null));
        shift.setMachine(this.machineRepository.findById(shiftModel.getMachine()).orElse(null));
        shift.setElement(this.elementRepository.findById(shiftModel.getElement()).orElse(null));

        Alloy alloy = this.alloyRepository.findById(shiftModel.getAlloy()).orElse(null);
        shift.setAlloy(alloy);

        if(alloy != null){
            int difference = shiftModel.getBarCount() + shiftModel.getWasteCount();
            alloy.setUnits(alloy.getUnits() - difference);
            BigDecimal updatedWeight = alloy.getWeight().subtract(alloy.getWeightPerUnit().multiply(BigDecimal.valueOf(difference)));
            alloy.setWeight(updatedWeight);
            alloyRepository.saveAndFlush(alloy);
        }

        this.shiftRepository.save(shift);
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
        this.shiftRepository.save(this.modelMapper.map(shiftBindingModel, Shift.class));
    }

    @Override
    public void deleteShift(String id) {
        this.shiftRepository.deleteById(id);
    }

    @Override
    public List<ShiftViewModel> findAllShiftsByDate(Date startDate, Date endDate) {
        return this.shiftRepository.findAll()
                .stream().filter(shift -> shift.getDateOfShift().after(startDate) && shift.getDateOfShift().before(endDate))
                .map(shift -> this.modelMapper.map(shift, ShiftViewModel.class))
                .sorted(Comparator.comparing(ShiftViewModel::getDateOfShift))
                .collect(Collectors.toList());
    }
}

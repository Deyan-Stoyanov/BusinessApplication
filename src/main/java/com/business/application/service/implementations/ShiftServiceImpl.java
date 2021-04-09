package com.business.application.service.implementations;

import com.business.application.entity.Shift;
import com.business.application.entity.binding.ShiftBindingModel;
import com.business.application.repository.ShiftRepository;
import com.business.application.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;

    @Autowired
    public ShiftServiceImpl(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Override
    public List<Shift> findShiftsByEmployeeId(String employeeId){
        return this.shiftRepository.findAllByEmployeeId(employeeId);
    }

    @Override
    public void recordNewShift(ShiftBindingModel shiftModel, String id) {

    }
}

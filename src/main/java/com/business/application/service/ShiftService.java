package com.business.application.service;

import com.business.application.entity.binding.ShiftBindingModel;
import com.business.application.entity.view.ShiftViewModel;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ShiftService {

    List<ShiftViewModel> findShiftsByEmployeeId(String employeeId);

    void recordNewShift(ShiftBindingModel shiftModel, String id);

    List<ShiftViewModel> findAllShifts();

    void addNewShift(ShiftBindingModel shiftBindingModel, BindingResult bindingResult);

    void deleteShift(String id);
}

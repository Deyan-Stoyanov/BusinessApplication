package com.business.application.service;

import com.business.application.entity.Shift;
import com.business.application.entity.binding.ShiftBindingModel;

import java.util.List;

public interface ShiftService {

    List<Shift> findShiftsByEmployeeId(String employeeId);

    void recordNewShift(ShiftBindingModel shiftModel, String id);
}

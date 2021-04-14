package com.business.application.service;

import com.business.application.entity.binding.EmployeeBindingModel;
import com.business.application.entity.view.EmployeeViewModel;
import com.business.application.exceptions.CreateOrUpdateEmployeeException;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface EmployeeService {
    void doRegisterOrUpdateEmployee(EmployeeBindingModel employeeModel, String userId, BindingResult bindingResult) throws CreateOrUpdateEmployeeException;

    EmployeeViewModel findEmployeeByUserId(String userId);

    List<EmployeeViewModel> findAllEmployees();

    void editEmployee(String id, EmployeeBindingModel employeeModel, BindingResult bindingResult);

    void deleteEmployee(String id);
}

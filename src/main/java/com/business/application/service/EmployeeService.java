package com.business.application.service;

import com.business.application.entity.Employee;
import com.business.application.entity.binding.EmployeeBindingModel;
import com.business.application.exceptions.CreateOrUpdateEmployeeException;
import org.springframework.validation.BindingResult;

public interface EmployeeService {
    void doRegisterOrUpdateEmployee(EmployeeBindingModel employeeModel, String userId, BindingResult bindingResult) throws CreateOrUpdateEmployeeException;

    Employee findEmployeeByUserId(String userId);
}

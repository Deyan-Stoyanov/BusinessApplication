package com.business.application.service.implementations;

import com.business.application.entity.Employee;
import com.business.application.entity.User;
import com.business.application.entity.binding.EmployeeBindingModel;
import com.business.application.exceptions.CreateOrUpdateEmployeeException;
import com.business.application.repository.EmployeeRepository;
import com.business.application.service.EmployeeService;
import com.business.application.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserService userService, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void doRegisterOrUpdateEmployee(EmployeeBindingModel employeeModel, String userId, BindingResult bindingResult) throws CreateOrUpdateEmployeeException {
        Optional<Employee> employeeByContractNumber = this.employeeRepository
                .findEmployeeByContractNumber(employeeModel.getContractNumber());
        Employee employee = employeeByContractNumber
                .map(value -> this.mapNewValuesToExistingEmployee(employeeModel, value))
                .orElseGet(() -> this.modelMapper.map(employeeModel, Employee.class));
        try {
            User user = this.userService.findUserById(userId);
            employee.setUser(user);
            this.employeeRepository.saveAndFlush(employee);
            if (user != null) {
                user.setEmployee(employee);
                this.userService.saveAndFlushUser(user);
            }
        } catch (Exception e) {
            throw new CreateOrUpdateEmployeeException();
        }
    }

    @Override
    public Employee findEmployeeByUserId(String userId) {
        return this.employeeRepository.findByUserId(userId).orElse(null);
    }

    private Employee mapNewValuesToExistingEmployee(EmployeeBindingModel employeeModel, Employee employeeByContractNumber) {
        employeeByContractNumber.setFirstName(employeeModel.getFirstName());
        employeeByContractNumber.setLastName(employeeModel.getLastName());
        employeeByContractNumber.setGender(employeeModel.getGender());
        employeeByContractNumber.setCity(employeeModel.getCity());
        employeeByContractNumber.setStreet(employeeModel.getStreet());
        employeeByContractNumber.setHouseNumber(employeeModel.getHouseNumber());
        return employeeByContractNumber;
    }
}

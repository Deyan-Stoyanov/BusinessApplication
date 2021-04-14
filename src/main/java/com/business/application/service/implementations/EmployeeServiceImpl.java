package com.business.application.service.implementations;

import com.business.application.entity.Employee;
import com.business.application.entity.User;
import com.business.application.entity.binding.EmployeeBindingModel;
import com.business.application.entity.view.EmployeeViewModel;
import com.business.application.exceptions.CreateOrUpdateEmployeeException;
import com.business.application.repository.EmployeeRepository;
import com.business.application.service.EmployeeService;
import com.business.application.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public EmployeeViewModel findEmployeeByUserId(String userId) {
        return this.modelMapper.map(this.employeeRepository.findByUserId(userId).orElse(new Employee()), EmployeeViewModel.class);
    }

    @Override
    public List<EmployeeViewModel> findAllEmployees() {
        return this.employeeRepository
                .findAll()
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeViewModel.class))
                .sorted(Comparator.comparing(EmployeeViewModel::getFirstName))
                .collect(Collectors.toList());
    }

    @Override
    public void editEmployee(String id, EmployeeBindingModel employeeModel, BindingResult bindingResult) {

    }

    @Override
    public void deleteEmployee(String id) {
        this.employeeRepository.deleteById(id);
        this.userService.doDeleteAccountByEmployeeId(id);
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

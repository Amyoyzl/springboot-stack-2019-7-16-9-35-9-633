package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getEmployees() {
        return repository.getEmployees();
    }

    public Employee getEmployee(int id) {
        return repository.getEmployee(id);
    }

    public List<Employee> getPageEmployees(int page, int pageSize) {
        return repository.getPageEmployees(page, pageSize);
    }

    public List<Employee> getEmployeesBySex(String gender) {
        return repository.getEmployeesBySex(gender);
    }

    public Employee addEmployee(Employee employee) {
        return repository.addEmployee(employee);
    }

    public Employee updateEmployee(Employee employee, int id) {
        return repository.updateEmployee(employee, id);
    }

    public Employee delete(int id) {
        return repository.delete(id);
    }
}

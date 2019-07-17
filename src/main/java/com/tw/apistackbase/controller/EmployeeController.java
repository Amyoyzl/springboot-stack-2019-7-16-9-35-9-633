package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping(path = "/employees", params = {"page", "pageSize"})
    public List<Employee> getPageEmployees(@RequestParam int page, @RequestParam int pageSize) {
        return employeeService.getPageEmployees(page, pageSize);
    }

    @GetMapping(path = "/employees", params = "gender")
    public List<Employee> getEmployeesBySex(@RequestParam String gender) {
        return employeeService.getEmployeesBySex(gender);
    }

}

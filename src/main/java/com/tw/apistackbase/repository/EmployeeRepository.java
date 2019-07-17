package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    private static Map<Integer, Employee> employees = new HashMap<>();
    private static int id = 1;

    static {
        employees.put(id++, new Employee(1, "Steve", 34, "Male", 23000));
        employees.put(id++, new Employee(2, "John", 23, "Male", 12000));
        employees.put(id++, new Employee(3, "Jimmy", 23, "Male", 10000));
        employees.put(id++, new Employee(4, "Jerry", 23, "Male", 10000));
        employees.put(id++, new Employee(5, "Laura", 23, "Female", 10000));
        employees.put(id++, new Employee(6, "Sean", 23, "Male", 10000));
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public List<Employee> getPageEmployees(int page, int pageSize) {
        return employees.values().stream().skip((Math.max(0, page - 1) * pageSize)).limit(pageSize).collect(Collectors.toList());
    }

    public List<Employee> getEmployeesBySex(String gender) {
        return employees.values().stream().filter(employee -> gender.equals(employee.getGender())).collect(Collectors.toList());
    }

    public Employee addEmployee(Employee employee) {
        employees.put(id++, employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee, int id) {
        employees.put(id, employee);
        return employee;
    }
}

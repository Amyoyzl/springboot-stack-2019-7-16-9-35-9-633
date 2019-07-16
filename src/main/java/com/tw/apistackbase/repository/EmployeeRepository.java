package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {

    private static Map<Integer, Employee> employees = new HashMap<>();

    static {
        employees.put(1, new Employee(1, "Steve", 34, "Male", 23000));
        employees.put(2, new Employee(2, "John", 23, "Male", 12000));
        employees.put(3, new Employee(3, "Jimmy", 23, "Male", 10000));
        employees.put(4, new Employee(4, "Jerry", 23, "Male", 10000));
        employees.put(5, new Employee(5, "Laura", 23, "Female", 10000));
        employees.put(6, new Employee(6, "Sean", 23, "Male", 10000));
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }
}

package com.tw.apistackbase.model;

import java.util.Arrays;
import java.util.List;

public class Company {
    private String companyName;
    private int employeesNumber;
    private List<Employee> employees;

    public Company(String companyName, Employee... employees) {
        this.companyName = companyName;
        this.employees = Arrays.asList(employees);
        this.employeesNumber = this.employees.size();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

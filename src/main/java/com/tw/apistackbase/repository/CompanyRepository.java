package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {

    private static Map<Integer, Company> companies = new HashMap<>();
    private static int id = 1;

    static {
        companies.put(id++, new Company("OOCL",
                new Employee(1, "Steve", 34, "Male", 23000),
                new Employee(2, "John", 23, "Male", 12000),
                new Employee(3, "Jimmy", 23, "Male", 10000),
                new Employee(4, "Jerry", 23, "Male", 10000),
                new Employee(5, "Laura", 23, "Female", 10000),
                new Employee(6, "Sean", 23, "Male", 10000)
        ));
    }

    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }

    public Company getById(int id) {
        return companies.get(id);
    }

    public List<Employee> getEmployees(int id) {
        return companies.get(id).getEmployees();
    }

    public List<Company> getPageCompanies(int page, int pageSize) {
        return companies.values().stream().skip((Math.max(0, page-1) * pageSize)).limit(pageSize).collect(Collectors.toList());
    }

    public Company createCompany(Company company) {
        return companies.put(id++, company);
    }

    public Company updateCompany(Company company, int id) {
        companies.remove(id);
        return companies.put(id, company);
    }

    public Company deleteCompany(int id) {
        return companies.remove(id);
    }
}

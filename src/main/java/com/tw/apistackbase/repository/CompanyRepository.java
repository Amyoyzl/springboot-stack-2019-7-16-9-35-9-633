package com.tw.apistackbase.repository;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CompanyRepository {

    private static Map<Integer, Company> companies = new HashMap<>();
    private static int id = 1;

    static {
        companies.put(id++, new Company("OOCL",
                new Employee(1, "Steve", 34, "Male", 23000),
                new Employee(2, "John", 23, "Male", 12000),
                new Employee(2, "Jimmy", 23, "Male", 10000),
                new Employee(2, "Jerry", 23, "Male", 10000),
                new Employee(2, "Laura", 23, "Female", 10000),
                new Employee(2, "Sean", 23, "Male", 10000)
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
        List<Company> pageCompanies = new ArrayList<>();
        List<Company> companyList = new ArrayList<>(companies.values());
        int length = companyList.size();
        pageSize = pageSize * page > length ? length : pageSize;
        for (int i = 0; i < pageSize; i++) {
            pageCompanies.add(companyList.get(page * pageSize + i));
        }
        return pageCompanies;
    }

    public Company createCompany(Company company) {
        return companies.put(id++, company);
    }

    public Company updateCompany(Company company, int id) {
        companies.remove(id);
        return companies.put(id, company);
    }

    public void deleteCompany(int id) {
        companies.remove(id);
    }
}

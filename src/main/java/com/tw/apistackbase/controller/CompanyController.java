package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable int id) {
        return companyService.getCompany(id);
    }

    @GetMapping("/companies/{id}/employees")
    public List<Employee> getEmployees(@PathVariable int id) {
        return companyService.getEmployees(id);
    }

    @GetMapping(path = "/companies", params = {"page", "pageSize"})
    public List<Company> getPageCompanies(@RequestParam int page, @RequestParam int pageSize) {
        return companyService.getPageCompanies(page, pageSize);
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @PutMapping("/companies/{id}")
    public Company updateCompany(@RequestBody Company company, @PathVariable int id) {
        return companyService.updateCompany(company, id);
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(companyService.deleteCompany(id));
    }

}

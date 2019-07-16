package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}

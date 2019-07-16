package com.tw.apistackbase.service;

import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(int id) {
        return companyRepository.getById(id);
    }

}

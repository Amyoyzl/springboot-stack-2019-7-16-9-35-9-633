package com.tw.apistackbase;

import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyService companyService;

    @Test
    public void should_return_companies_list_when_send_get_request() throws Exception {
        Map<Integer, Company> companies = new HashMap<>();
        companies.put(1, new Company("OOCL", new Employee(1, "Steve", 34, "Male", 23000)));

        when(companyService.getCompanies()).thenReturn(new ArrayList<>(companies.values()));
        ResultActions result = mvc.perform(get("/companies"));

        result.andExpect(status().isOk()).andExpect(content().json("[{companyName: OOCL, employeesNumber: 1, employees:" +
                "[{id: 1, name: Steve, age: 34,gender: Male,salary: 23000}]}]"));
    }

    @Test
    public void should_return_specific_company_when_give_a_company_id() throws Exception {
        int id = 1;
        Company company = new Company("OOCL", new Employee(1, "Steve", 34, "Male", 23000));

        when(companyService.getCompany(id)).thenReturn(company);
        ResultActions result = mvc.perform(get("/companies/{id}", id));

        result.andExpect(status().isOk()).andExpect(content().json("{companyName: OOCL, employeesNumber: 1, employees:" +
                "[{id: 1, name: Steve, age: 34,gender: Male,salary: 23000}]}"));
    }

    @Test
    public void should_return_corresponding_company_employees_when_give_a_company_id() throws Exception {
        int id = 1;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Steve", 34, "Male", 23000));

        when(companyService.getEmployees(id)).thenReturn(employees);
        ResultActions result = mvc.perform(get("/companies/{id}/employees", id));

        result.andExpect(status().isOk()).andExpect(content().json("[{id: 1, name: Steve, age: 34,gender: Male,salary: 23000}]"));
    }

}

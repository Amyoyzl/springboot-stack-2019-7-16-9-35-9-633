package com.tw.apistackbase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.model.Company;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        result.andExpect(status().isOk()).andExpect(jsonPath("$[0].companyName", is("OOCL")));
        verify(companyService).getCompanies();
    }

    @Test
    public void should_return_specific_company_when_give_a_company_id() throws Exception {
        Company company = new Company("OOCL", new Employee(1, "Steve", 34, "Male", 23000));

        when(companyService.getCompany(anyInt())).thenReturn(company);
        ResultActions result = mvc.perform(get("/companies/{id}", anyInt()));

        result.andExpect(status().isOk()).andExpect(jsonPath("$.companyName", is("OOCL")));
        verify(companyService).getCompany(anyInt());
    }

    @Test
    public void should_return_corresponding_company_employees_when_give_a_company_id() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Steve", 34, "Male", 23000));

        when(companyService.getEmployees(anyInt())).thenReturn(employees);
        ResultActions result = mvc.perform(get("/companies/{id}/employees", anyInt()));

        result.andExpect(status().isOk()).andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Steve")));
        verify(companyService).getEmployees(anyInt());
    }

    @Test
    public void should_return_companies_when_give_page_and_pageSize() throws Exception {
        Map<Integer, Company> companies = new HashMap<>();
        companies.put(1, new Company("OOCL", new Employee(1, "Steve", 34, "Male", 23000)));

        when(companyService.getPageCompanies(anyInt(), anyInt())).thenReturn(new ArrayList<>(companies.values()));
        ResultActions result = mvc.perform(get("/companies", anyInt(), anyInt())
                .param("page", "1").param("pageSize", "3"));

        result.andExpect(status().isOk()).andExpect(jsonPath("$[0].companyName", is("OOCL")))
                .andExpect(jsonPath("$", hasSize(1)));
        verify(companyService).getPageCompanies(anyInt(),anyInt());
    }

    @Test
    public void should_return_status_is_isCreated_when_add_company() throws Exception {
        Company company = new Company("OOCL", new Employee(1, "John", 24, "Male", 1400));
        when(companyService.createCompany(any())).thenReturn(company);
        ResultActions resultActions = mvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(company)));
        resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.companyName", is("OOCL")));
    }

    @Test
    public void should_return_status_is_Ok_when_update_company() throws Exception {
        int companyId = 1;
        Company company = new Company();
        company.setCompanyName("test");
        when(companyService.updateCompany(any(), anyInt())).thenReturn(company);
        ResultActions resultActions = mvc.perform(put("/companies/{companyId}", companyId)
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(company)));
        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.companyName", is("test")));
    }

    @Test
    public void should_return_status_is_Ok_when_delete_company() throws Exception {
        ResultActions result = mvc.perform(delete("/companies/1"));
        result.andExpect(status().isNoContent());
    }

}

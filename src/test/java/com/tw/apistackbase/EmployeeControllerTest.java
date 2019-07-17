package com.tw.apistackbase;

import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.model.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void should_return_employee_list_when_send_get_request() throws Exception {
        Map<Integer, Employee> employees = new HashMap<>();
        employees.put(1, new Employee(1, "Steve", 34, "Male", 23000));

        when(employeeService.getEmployees()).thenReturn(new ArrayList<>(employees.values()));
        ResultActions result = mvc.perform(get("/employees"));

        result.andExpect(status().isOk())
                .andExpect(content().json("[{id: 1, name: Steve, age: 34,gender: Male,salary: 23000}]"));
    }

    @Test
    public void should_return_specific_employee_when_give_a_employee_id() throws Exception {
        int id = 1;
        Employee employee =  new Employee(1, "Steve", 34, "Male", 23000);

        when(employeeService.getEmployee(id)).thenReturn(employee);
        ResultActions result = mvc.perform(get("/employees/{id}", id));

        result.andExpect(status().isOk()).andExpect(content().json("{id: 1, name: Steve, age: 34, gender: Male,salary: 23000}"));
    }

    @Test
    public void should_return_employees_when_give_page_and_pageSize() throws Exception {
        Map<Integer, Employee> employees = new HashMap<>();
        employees.put(1, new Employee(1, "Steve", 34, "Male", 23000));
        employees.put(2, new Employee(2, "John", 23, "Male", 12000));

        when(employeeService.getPageEmployees(anyInt(), anyInt())).thenReturn(new ArrayList<>(employees.values()));
        ResultActions result = mvc.perform(get("/employees").param("page","1").param("pageSize", "3"));

        verify(employeeService).getPageEmployees(1,3);
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Steve")))
                .andExpect(jsonPath("$[1].age", is(23)));
    }

    @Test
    public void should_return_corresponding_employees_when_give_gender() throws Exception {
        Map<Integer, Employee> employees = new HashMap<>();
        employees.put(1, new Employee(1, "Steve", 34, "Female", 23000));
        employees.put(2, new Employee(2, "John", 23, "Female", 12000));

        when(employeeService.getEmployeesBySex(anyString())).thenReturn(new ArrayList<>(employees.values()));
        ResultActions result = mvc.perform(get("/employees").param("gender", "Female"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Steve")))
                .andExpect(jsonPath("$[1].age", is(23)));
        verify(employeeService).getEmployeesBySex("Female");
    }

}

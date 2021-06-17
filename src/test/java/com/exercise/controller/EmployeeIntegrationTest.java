package com.exercise.controller;

import com.exercise.model.Employee;
import com.exercise.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeIntegrationTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  @Autowired
  private EmployeeRepository employeeRepository;

  @BeforeEach
  void setup() {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }

  @AfterEach
  void teardown() {
    employeeRepository.deleteAll();
  }

  @WithMockUser(value = "spring")
  @Test
  void should_return_employee_when_call_GET_employee_id_1() throws Exception {

    employeeRepository.saveAll(newArrayList(Employee.builder()
        .id(1)
        .name("John")
        .surname("Snow")
        .build(), Employee.builder()
        .id(2)
        .name("John")
        .surname("Smith")
        .build()));

//    mvc.perform(get("/v1/employee/1")).andExpect(content().string("test"));
    mvc.perform(get("/v1/employee/1")).andExpect(status().isOk());

  }

}
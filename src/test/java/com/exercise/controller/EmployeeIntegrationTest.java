package com.exercise.controller;

import com.exercise.model.Employee;
import com.exercise.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EmployeeIntegrationTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  @Autowired
  private EmployeeRepository employeeRepository;

  @BeforeEach
  void setupEach() {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
  }

  @WithMockUser(value = "spring")
  @Test
  void should_return_all_employee_when_send_GET_request() throws Exception {

    //Given
    initializeDataInH2();

    //When
    //Then
    mvc.perform(get("/v1/employee/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name").value("John"))
        .andExpect(jsonPath("$[0].surname").value("Snow"))
        .andExpect(jsonPath("$[1].name").value("John"))
        .andExpect(jsonPath("$[1].surname").value("Smith"));
  }

  @WithMockUser(value = "spring")
  @Test
  void should_return_an_employee_when_send_GET_request_given_employee_id() throws Exception {

    //Given
    initializeDataInH2();

    //When
    //Then
    mvc.perform(get("/v1/employee/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json("{\"id\":1,\"name\":\"John\",\"surname\":\"Snow\"}"));
  }

  private void initializeDataInH2() {
    employeeRepository.saveAll(newArrayList(Employee.builder()
        .id(1)
        .name("John")
        .surname("Snow")
        .build(), Employee.builder()
        .id(2)
        .name("John")
        .surname("Smith")
        .build()));
  }
}
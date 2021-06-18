package com.exercise.controller;

import com.exercise.dto.EmployeeRequestDTO;
import com.exercise.dto.EmployeeResponseDTO;
import com.exercise.mapper.EmployeeMapper;
import com.exercise.model.Employee;
import com.exercise.services.EmployeeService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

  @InjectMocks
  EmployeeController employeeController;

  @Mock
  EmployeeService employeeService;

  @Mock
  EmployeeMapper employeeMapper;

  @Test
  void should_get_all_employee_response_dto_when_call_get_all_employee() {
    //Given
    EmployeeResponseDTO employeeResponseDTO = EmployeeResponseDTO.builder()
        .id(1)
        .name("John")
        .surname("Snow")
        .build();

    when(employeeService.getAllEmployee()).thenReturn(newArrayList(Employee.builder()
        .id(1)
        .name("John")
        .surname("Snow")
        .build()));

    //When
    List<EmployeeResponseDTO> actual = employeeController.getAllEmployee();

    //Then
    assertEquals(employeeResponseDTO, actual.get(0));
  }

  @Test
  void should_get_an_employee_response_dto_when_call_get_employee_given_employee_id() {
    //Given
    when(employeeService.getEmployeeById(1)).thenReturn(Employee.builder()
        .id(1)
        .name("John")
        .surname("Snow")
        .build());

    //When
    EmployeeResponseDTO actual = employeeController.getEmployee(1);

    //Then
    assertEquals(EmployeeResponseDTO.builder()
        .id(1)
        .name("John")
        .surname("Snow")
        .build(), actual);
  }

  @Test
  void should_save_an_employee_when_call_save_employee_given_employee_dto_request() {
    //Given
    Employee employee = Employee.builder()
        .name("John")
        .surname("Snow")
        .build();

    //When
    employeeController.saveEmployee(EmployeeRequestDTO.builder()
        .name("John")
        .surname("Snow")
        .build());

    //Then
    verify(employeeService).saveEmployee(employee);
  }

  @Test
  void should_update_an_employee_when_call_save_employee_given_employee_id_and_employee_dto_request() {
    //Given
    Employee employee = Employee.builder()
        .name("John")
        .surname("Snow")
        .build();

    //When
    employeeController.updateEmployee(1, EmployeeRequestDTO.builder()
        .name("John")
        .surname("Snow")
        .build());

    //Then
    verify(employeeService).updateEmployee(1, employee);
  }

  @Test
  void should_delete_an_employee_when_call_save_employee_given_employee_id() {
    //Given
    //When
    employeeController.deleteEmployee(1);

    //Then
    verify(employeeService).deleteEmployee(1);
  }

}
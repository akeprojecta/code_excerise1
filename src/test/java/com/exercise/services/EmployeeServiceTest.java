package com.exercise.services;

import com.exercise.model.Employee;
import com.exercise.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

  @Mock
  EmployeeRepository employeeRepository;

  @InjectMocks
  EmployeeService employeeService;

  @Test
  void should_return_list_of_all_employee_when_call_get_all_employee() {

    //Given
    when(employeeRepository.findAll()).thenReturn(newArrayList(Employee.builder()
        .id(1)
        .name("Prayut")
        .surname("ChaOChan")
        .build()));

    //When
    List<Employee> actual = employeeService.getAllEmployee();

    //Then
    assertEquals(newArrayList(Employee.builder()
        .id(1)
        .name("Prayut")
        .surname("ChaOChan")
        .build()), actual);
  }

  @Test
  void should_return_employee_when_call_get_employee_by_id_given_id() {

    //Given
    when(employeeRepository.findById(new Long(1))).thenReturn(Optional.of(Employee.builder()
        .id(1)
        .name("Prayut")
        .surname("ChaOChan")
        .build()));

    //When
    Employee actual = employeeService.getEmployeeById(1);

    assertEquals(Employee.builder()
        .id(1)
        .name("Prayut")
        .surname("ChaOChan")
        .build(), actual);
  }
}
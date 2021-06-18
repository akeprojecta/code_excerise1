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
import static org.mockito.Mockito.verify;
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
  void should_return_an_employee_when_call_get_employee_by_id_given_id() {

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

  @Test
  void should_save_an_employee_when_call_save_employee_given_employee() {
    //Given
    Employee employee = Employee.builder()
        .name("John")
        .surname("Snow")
        .build();
    //When
    employeeService.saveEmployee(employee);
    //Then

    verify(employeeRepository).save(employee);
  }

  @Test
  void should_update_an_employee_when_call_update_employee_given_employee_id_and_employee() {
    //Given
    Employee employee = Employee.builder()
        .name("John")
        .surname("Snow")
        .build();

    when(employeeRepository.findById(new Long(1))).thenReturn(Optional.of(employee));
    //When
    employeeService.updateEmployee(1, employee);
    //Then

    verify(employeeRepository).findById(new Long(1));
    verify(employeeRepository).save(employee);
  }

  @Test
  void should_delete_an_employee_when_call_delete_employee_give_employee_id() {
    //Given
    //When
    employeeService.deleteEmployee(new Long(1));
    //Then
    verify(employeeRepository).deleteById(new Long(1));
  }
}
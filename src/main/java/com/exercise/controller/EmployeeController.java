package com.exercise.controller;

import com.exercise.dto.EmployeeRequestDTO;
import com.exercise.dto.EmployeeResponseDTO;
import com.exercise.mapper.EmployeeMapper;
import com.exercise.model.Employee;
import com.exercise.services.EmployeeService;
import java.util.List;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/employee")
public class EmployeeController {

  private final EmployeeService employeeService;
  private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping
  public List<EmployeeResponseDTO> getAllEmployee() {
    return employeeMapper.EmployeesToEmployeeDTOs(employeeService.getAllEmployee());
  }

  @GetMapping(path = "/{id}")
  public EmployeeResponseDTO getEmployee(@PathVariable("id") int id) {
    return employeeMapper.EmployeeToEmployeeDTO(employeeService.getEmployeeById(id));
  }

  @PostMapping
  public void saveEmployee(EmployeeRequestDTO employeeRequestDTO) {
    Employee employee = employeeMapper.EmployeeRequestDTOToEmployee(employeeRequestDTO);
    employeeService.saveEmployee(employee);
  }

  @PutMapping(path = "/{id}")
  public void updateEmployee(@PathVariable("id") int id, EmployeeRequestDTO employeeRequestDTO) {
    Employee employee = employeeMapper.EmployeeRequestDTOToEmployee(employeeRequestDTO);
    employeeService.updateEmployee(id, employee);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteEmployee(@PathVariable("id") int id) {
    employeeService.deleteEmployee(id);
  }
}

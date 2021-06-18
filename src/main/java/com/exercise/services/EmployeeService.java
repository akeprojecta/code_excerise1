package com.exercise.services;

import com.exercise.exception.RecordNotFoundException;
import com.exercise.model.Employee;
import com.exercise.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class EmployeeService {

  final private EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public List<Employee> getAllEmployee() {
    return newArrayList(employeeRepository.findAll());
  }

  public Employee getEmployeeById(long id) {
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    if (employeeOptional.isPresent()) {
      return employeeOptional.get();
    }
    throw new RecordNotFoundException();
  }

  public void saveEmployee(Employee employee) {
    employeeRepository.save(employee);
  }

  public void updateEmployee(long id, Employee employee) {
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    if (employeeOptional.isPresent()) {
      employee.setId(id);
      employeeRepository.save(employee);
    }
  }

  public void deleteEmployee(long id) {
    employeeRepository.deleteById(id);
  }
}

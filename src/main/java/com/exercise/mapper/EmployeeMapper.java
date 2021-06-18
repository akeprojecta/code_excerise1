package com.exercise.mapper;

import com.exercise.dto.EmployeeRequestDTO;
import com.exercise.dto.EmployeeResponseDTO;
import com.exercise.model.Employee;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmployeeMapper {

  EmployeeResponseDTO EmployeeToEmployeeDTO(Employee employee);

  List<EmployeeResponseDTO> EmployeesToEmployeeDTOs(List<Employee> employees);

  @Mapping(target = "id", ignore = true)
  Employee EmployeeRequestDTOToEmployee(EmployeeRequestDTO employeeRequestDTO);
}

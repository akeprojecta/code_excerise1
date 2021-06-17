package com.exercise.mapper;

import com.exercise.dto.EmployeeRequestDTO;
import com.exercise.dto.EmployeeResponseDTO;
import com.exercise.model.Employee;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

//  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  EmployeeResponseDTO EmployeeToEmployeeDTO(Employee employee);

  List<EmployeeResponseDTO> EmployeesToEmployeeDTOs(List<Employee> employees);

  Employee EmployeeRequestDTOToEmployee(EmployeeRequestDTO employeeRequestDTO);
}

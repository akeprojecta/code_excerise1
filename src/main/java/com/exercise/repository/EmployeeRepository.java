package com.exercise.repository;

import com.exercise.model.Employee;
import com.exercise.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
package com.employee.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.project.entity.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer>{

}

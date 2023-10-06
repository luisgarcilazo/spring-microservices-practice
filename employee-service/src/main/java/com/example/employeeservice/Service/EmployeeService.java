package com.example.employeeservice.Service;

import com.example.employeeservice.DTO.EmployeeRequest;
import com.example.employeeservice.DTO.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse saveEmployee(EmployeeRequest employeeRequest);

    List<EmployeeResponse> findAll();

    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest);

    String deleteEmployeeById(Long id);

    List<EmployeeResponse> getEmployeesByDepartmentId(Long id);
}

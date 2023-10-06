package com.example.departmentservice.Service;

import com.example.departmentservice.DTO.DepartmentRequest;
import com.example.departmentservice.DTO.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse saveDepartment(DepartmentRequest departmentRequest);

    List<DepartmentResponse> findAll();

    DepartmentResponse getById(Long id);

    DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest);

    String deleteDepartmentById(Long id);
}

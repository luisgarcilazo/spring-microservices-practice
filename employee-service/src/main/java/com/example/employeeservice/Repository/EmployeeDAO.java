package com.example.employeeservice.Repository;

import com.example.employeeservice.Entity.Employee;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.IValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM tbl_employee WHERE department_id = ?1", nativeQuery=true)
    List<Employee> getEmployeesByDepartmentId(Long id);
}

package com.example.employeeservice.Controller;

import com.example.employeeservice.DTO.EmployeeRequest;
import com.example.employeeservice.DTO.EmployeeResponse;
import com.example.employeeservice.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //POST /employees
    //CREATE NEW EMPLOYEE
    @PostMapping("")
    public EmployeeResponse saveEmployee(@RequestBody EmployeeRequest employeeRequest){
        return employeeService.saveEmployee(employeeRequest);
    }

    //GET /employees
    //GET A LIST OF ALL EMPLOYEES
    @GetMapping("")
    public List<EmployeeResponse> getEmployees(){
        return employeeService.findAll();
    }

    //GET /employees/{id}
    //GET employee by id
    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    //PUT /employees/{id}
    //UPDATE EMPLOYEE BY ID
    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Long id,
                                           @RequestBody EmployeeRequest employeeRequest){
        return employeeService.updateEmployee(id, employeeRequest);
    }

    //DELETE /employees/{id}
    //DELETE EMPLOYEE BY ID
    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable Long id){
        return employeeService.deleteEmployeeById(id);
    }

    //GET /employees/department/{id}
    //GET EMPLOYEES BY DEPARTMENT ID
    @GetMapping("/department/{id}")
    public List<EmployeeResponse> getEmployeesByDepartmentId(@PathVariable Long id){
        return employeeService.getEmployeesByDepartmentId(id);
    }
}

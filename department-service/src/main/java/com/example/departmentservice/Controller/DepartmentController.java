package com.example.departmentservice.Controller;

import com.example.departmentservice.DTO.DepartmentRequest;
import com.example.departmentservice.DTO.DepartmentResponse;
import com.example.departmentservice.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    //POST /departments
    //CREATE NEW DEPARTMENT
    @PostMapping("")
    public DepartmentResponse saveDepartment(@RequestBody DepartmentRequest departmentRequest){
        return this.departmentService.saveDepartment(departmentRequest);
    }

    //GET /departments
    //RETRIEVE ALL THE DEPARTMENTS
    @GetMapping("")
    public List<DepartmentResponse> getDepartments(){
        return this.departmentService.findAll();
    }

    //GET /departments/{id}
    //RETRIEVE DEPARTMENT BY ID
    @GetMapping("/{id}")
    public DepartmentResponse getDepartmentById(@PathVariable Long id){
        return this.departmentService.getById(id);
    }

    //PUT /departments/{id}
    //UPDATE DEPARTMENT BY ID
    @PutMapping("/{id}")
    public DepartmentResponse updateDepartment(@PathVariable Long id,
                                               @RequestBody DepartmentRequest departmentRequest){
        return this.departmentService.updateDepartment(id, departmentRequest);
    }

    //DELETE /departments/{id}
    //DELETE A DEPARTMENT BY ID
    @DeleteMapping("/{id}")
    public String deleteDepartmentById(@PathVariable Long id){
        return this.departmentService.deleteDepartmentById(id);
    }
}

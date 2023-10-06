package com.example.departmentservice.Service;

import com.example.departmentservice.DTO.DepartmentRequest;
import com.example.departmentservice.DTO.DepartmentResponse;
import com.example.departmentservice.Entity.Department;
import com.example.departmentservice.Repository.DepartmentDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDAO departmentDAO;

    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }


    @Override
    public DepartmentResponse saveDepartment(DepartmentRequest departmentRequest) {
        if(departmentRequest.getDepartmentName() == null || departmentRequest.getBudget() == null
           || departmentRequest.getLocation() == null){
            throw new RuntimeException("Invalid input to save department request.");
        }
        Department departmentToSubmit = new Department();
        departmentToSubmit.setDepartmentName(departmentRequest.getDepartmentName());
        departmentToSubmit.setLocation(departmentRequest.getLocation());
        departmentToSubmit.setBudget(departmentRequest.getBudget());
        return mapDepartmentToDepartmentResponse(this.departmentDAO.save(departmentToSubmit));
    }



    @Override
    public List<DepartmentResponse> findAll() {
        return mapDepartmentListToDepartmentResponseList(this.departmentDAO.findAll());
    }



    @Override
    public DepartmentResponse getById(Long id) {
        return mapDepartmentToDepartmentResponse(this.departmentDAO.findById(id).orElseThrow(() -> new RuntimeException("Invalid id: Couldn't find department.")));
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest) {
        Department department = this.departmentDAO.findById(id).orElseThrow(() -> new RuntimeException("Invalid id: Couldn't process update request for id " + id +"."));
        department.setDepartmentName(departmentRequest.getDepartmentName());
        department.setLocation(departmentRequest.getLocation());
        department.setBudget(departmentRequest.getBudget());
        return mapDepartmentToDepartmentResponse(this.departmentDAO.save(department));
    }

    @Override
    public String deleteDepartmentById(Long id) {

        Optional<Department> optDepartment = this.departmentDAO.findById(id);
        if(optDepartment.isPresent()){
            this.departmentDAO.delete(optDepartment.get());
            return "Successfully called delete for id: " + id;
        }
        return "Couldn't find department for id: " + id + ". Delete request failed.";
    }

    private DepartmentResponse mapDepartmentToDepartmentResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getDepartmentName(),
                department.getLocation(),
                department.getBudget()
        );
    }

    private List<DepartmentResponse> mapDepartmentListToDepartmentResponseList(List<Department> departments) {
        List<DepartmentResponse> departmentResponseList = new ArrayList<>();
        for(Department department: departments){
            DepartmentResponse departmentResponse = new DepartmentResponse(
                    department.getId(),
                    department.getDepartmentName(),
                    department.getLocation(),
                    department.getBudget()
            );
            departmentResponseList.add(departmentResponse);
        }
        return departmentResponseList;
    }
}

package com.example.employeeservice.Service;

import com.example.employeeservice.DTO.DepartmentResponse;
import com.example.employeeservice.Entity.Employee;
import com.example.employeeservice.DTO.EmployeeRequest;
import com.example.employeeservice.DTO.EmployeeResponse;
import com.example.employeeservice.Repository.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    private RestTemplate restTemplate;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO, RestTemplate restTemplate){
        this.employeeDAO = employeeDAO;
        this.restTemplate = restTemplate;
    }


    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        if(employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null
          || employeeRequest.getDepartmentId() == null || employeeRequest.getEmailAddress() == null){
            throw new RuntimeException("Invalid input to save employee request.");
        }
        Employee employeeToSubmit = new Employee();
        employeeToSubmit.setFirstName(employeeRequest.getFirstName());
        employeeToSubmit.setLastName(employeeRequest.getLastName());
        employeeToSubmit.setDepartmentId(employeeRequest.getDepartmentId());
        employeeToSubmit.setEmailAddress(employeeRequest.getEmailAddress());

        return mapEmployeeToEmployeeResponse(this.employeeDAO.save(employeeToSubmit));
    }



    @Override
    public List<EmployeeResponse> findAll() {
        return mapEmployeeListToEmployeeResponseList(this.employeeDAO.findAll());
    }



    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = this.employeeDAO.findById(id).orElseThrow(() -> new RuntimeException("Invalid id: Couldn't find employee for id: " + id));
        return mapEmployeeToEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = this.employeeDAO.findById(id).orElseThrow(() -> new RuntimeException("Invalid id: Couldn't process update request for id " + id + "."));
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmailAddress(employeeRequest.getEmailAddress());
        employee.setDepartmentId(employeeRequest.getDepartmentId());
        return mapEmployeeToEmployeeResponse(this.employeeDAO.save(employee));
    }

    @Override
    public String deleteEmployeeById(Long id) {
        Optional<Employee> optEmployee =this.employeeDAO.findById(id);
        if(optEmployee.isPresent()){
            this.employeeDAO.delete(optEmployee.get());
            return "Successfully called delete for employee id: " + id;
        }
        return "Couldn't find employee for id: " + id +". Delete request failed.";
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartmentId(Long id) {
        return mapEmployeeListToEmployeeResponseList(this.employeeDAO.getEmployeesByDepartmentId(id));
    }
    private List<EmployeeResponse> mapEmployeeListToEmployeeResponseList(List<Employee> employees) {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        for(Employee employee: employees){
            DepartmentResponse departmentResponse = restTemplate.getForObject("http://localhost:8081/departments/{id}",DepartmentResponse.class,employee.getDepartmentId());
            System.out.println(departmentResponse.toString());
            employeeResponseList.add(new EmployeeResponse(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmailAddress(),
                    departmentResponse
            ));
        }
        return employeeResponseList;
    }
    private EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee) {
        DepartmentResponse departmentResponse = restTemplate.getForObject("http://localhost:8081/departments/{id}",DepartmentResponse.class,employee.getDepartmentId());
        System.out.println(departmentResponse.toString());
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmailAddress(),
                departmentResponse
        );
    }
}

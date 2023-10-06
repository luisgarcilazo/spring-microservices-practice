package com.example.departmentservice.DTO;

import java.math.BigDecimal;

public class DepartmentResponse {


    private Long id;
    private String departmentName;

    private String location;

    private BigDecimal budget;

    public DepartmentResponse(){}

    public DepartmentResponse(Long id,
                              String departmentName,
                              String location,
                              BigDecimal budget){
        this.id = id;
        this.departmentName = departmentName;
        this.location = location;
        this.budget = budget;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

}

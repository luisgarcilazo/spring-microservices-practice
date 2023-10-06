package com.example.departmentservice.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;


public class DepartmentRequest {

    public DepartmentRequest(){}

    public DepartmentRequest(String departmentName,
                             String location,
                             BigDecimal budget){
        this.departmentName = departmentName;
        this.location = location;
        this.budget = budget;
    }
    private String departmentName;


    private String location;


    private BigDecimal budget;

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

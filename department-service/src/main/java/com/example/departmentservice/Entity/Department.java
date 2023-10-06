package com.example.departmentservice.Entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="tbl_department")
public class Department {


    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(name = "departmentName")
    private String departmentName;

    @Column(name = "location")
    private String location;

    @Column(name = "budget")
    private BigDecimal budget;

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

package com.aditya.campusplacementtracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private Double packageOffered;

    private Double minimumCgpa;

    private String location;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getPackageOffered() {
        return packageOffered;
    }

    public void setPackageOffered(Double packageOffered) {
        this.packageOffered = packageOffered;
    }

    public Double getMinimumCgpa() {
        return minimumCgpa;
    }

    public void setMinimumCgpa(Double minimumCgpa) {
        this.minimumCgpa = minimumCgpa;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Company(Long id, String companyName, Double packageOffered, Double minimumCgpa, String location) {
        this.id = id;
        this.companyName = companyName;
        this.packageOffered = packageOffered;
        this.minimumCgpa = minimumCgpa;
        this.location = location;

    }
}

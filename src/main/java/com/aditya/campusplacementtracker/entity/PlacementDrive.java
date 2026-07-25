package com.aditya.campusplacementtracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class PlacementDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String driveName;

    private String companyName;

    private Double packageOffered;

    private Double minimumCgpa;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate driveDate;


    private String location;

    public PlacementDrive() {
    }

    public PlacementDrive(Long id,
                          String driveName,
                          String companyName,
                          Double packageOffered,
                          Double minimumCgpa,
                          LocalDate driveDate,
                          String location) {

        this.id = id;
        this.driveName = driveName;
        this.companyName = companyName;
        this.packageOffered = packageOffered;
        this.minimumCgpa = minimumCgpa;
        this.driveDate = driveDate;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
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

    public LocalDate getDriveDate() {
        return driveDate;
    }

    public void setDriveDate(LocalDate driveDate) {
        this.driveDate = driveDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
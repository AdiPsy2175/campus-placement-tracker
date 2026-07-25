package com.aditya.campusplacementtracker.dto;

public class ApplicationView {

    private Long id;
    private String studentName;
    private String driveName;
    private String status;

    public ApplicationView() {
    }

    public ApplicationView(Long id,
                           String studentName,
                           String driveName,
                           String status) {
        this.id = id;
        this.studentName = studentName;
        this.driveName = driveName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
package com.aditya.campusplacementtracker.service;

import com.aditya.campusplacementtracker.dto.ApplicationView;
import com.aditya.campusplacementtracker.entity.Application;
import com.aditya.campusplacementtracker.entity.PlacementDrive;
import com.aditya.campusplacementtracker.entity.Student;
import com.aditya.campusplacementtracker.repository.ApplicationRepository;
import com.aditya.campusplacementtracker.repository.PlacementDriveRepository;
import com.aditya.campusplacementtracker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PlacementDriveRepository placementDriveRepository;

    public ApplicationResult saveApplication(Application application) {

        Student student = studentRepository.findById(application.getStudentId()).orElse(null);

        PlacementDrive drive = placementDriveRepository.findById(application.getDriveId()).orElse(null);

        if (student == null) {
            return new ApplicationResult(false, "Student not found.");
        }

        if (drive == null) {
            return new ApplicationResult(false, "Placement drive not found.");
        }

        if (student.getCgpa() < drive.getMinimumCgpa()) {
            return new ApplicationResult(false,
                    "You are not eligible for this drive.");
        }

        if (applicationRepository.existsByStudentIdAndDriveId(
                application.getStudentId(),
                application.getDriveId())) {

            return new ApplicationResult(false,
                    "You have already applied for this drive.");
        }

        applicationRepository.save(application);

        return new ApplicationResult(true,
                "Application submitted successfully.");
    }

    public List<Application> getAllApplications() {

        List<Application> list = applicationRepository.findAll();

        for (Application app : list) {
            System.out.println("ID = " + app.getId());
            System.out.println("Student = " + app.getStudentId());
            System.out.println("Drive = " + app.getDriveId());
            System.out.println("Status = " + app.getStatus());
            System.out.println("----------------------");
        }

        return list;
    }

    public List<ApplicationView> getAllApplicationViews() {

        List<Application> applications = applicationRepository.findAll();

        List<ApplicationView> views = new ArrayList<>();

        for (Application app : applications) {

            Student student = studentRepository
                    .findById(app.getStudentId())
                    .orElse(null);

            PlacementDrive drive = placementDriveRepository
                    .findById(app.getDriveId())
                    .orElse(null);

            String studentName = student != null
                    ? student.getName()
                    : "Unknown Student";

            String driveName = drive != null
                    ? drive.getDriveName()
                    : "Unknown Drive";

            ApplicationView view = new ApplicationView(
                    app.getId(),
                    studentName,
                    driveName,
                    app.getStatus()
            );

            views.add(view);
        }

        return views;
    }

    public List<ApplicationView> searchApplications(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllApplicationViews();
        }

        List<Student> students =
                studentRepository.findByNameContainingIgnoreCase(keyword);

        List<ApplicationView> views = new ArrayList<>();

        for (Student student : students) {

            List<Application> applications =
                    applicationRepository.findByStudentId(student.getId());

            for (Application app : applications) {

                PlacementDrive drive =
                        placementDriveRepository.findById(app.getDriveId()).orElse(null);

                String driveName = "";

                if (drive != null) {
                    driveName = drive.getDriveName();
                }

                views.add(new ApplicationView(
                        app.getId(),
                        student.getName(),
                        driveName,
                        app.getStatus()
                ));
            }
        }

        return views;
    }

    public Page<ApplicationView> getApplicationViews(int page) {

        Page<Application> applications =
                applicationRepository.findAll(PageRequest.of(page, 5));

        List<ApplicationView> views = new ArrayList<>();

        for (Application app : applications) {

            Student student =
                    studentRepository.findById(app.getStudentId()).orElse(null);

            PlacementDrive drive =
                    placementDriveRepository.findById(app.getDriveId()).orElse(null);

            String studentName = "";

            if (student != null)
                studentName = student.getName();

            String driveName = "";

            if (drive != null)
                driveName = drive.getDriveName();

            views.add(new ApplicationView(
                    app.getId(),
                    studentName,
                    driveName,
                    app.getStatus()
            ));
        }

        return new PageImpl<>(
                views,
                applications.getPageable(),
                applications.getTotalElements()
        );
    }


    public long getApplicationCount() {
        return applicationRepository.count();
    }


    public long getPendingApplicationCount() {
        return applicationRepository.countByStatus("Pending");
    }

    public long getSelectedApplicationCount() {
        return applicationRepository.countByStatus("Selected");
    }

    public long getRejectedApplicationCount() {
        return applicationRepository.countByStatus("Rejected");
    }

    public List<Application> getApplicationsByStatus(String status) {
        return applicationRepository.findByStatus(status);
    }

    public void updateStatus(Long id, String status) {

        Application application =
                applicationRepository.findById(id).orElse(null);

        if (application != null) {

            application.setStatus(status);

            applicationRepository.save(application);
        }
    }

    public List<ApplicationView> getApplicationViewsByStatus(String status) {

        List<Application> applications =
                applicationRepository.findByStatus(status);

        List<ApplicationView> views = new ArrayList<>();

        for (Application app : applications) {

            Student student =
                    studentRepository.findById(app.getStudentId()).orElse(null);

            PlacementDrive drive =
                    placementDriveRepository.findById(app.getDriveId()).orElse(null);

            String studentName = "";

            if (student != null) {
                studentName = student.getName();
            }

            String driveName = "";

            if (drive != null) {
                driveName = drive.getDriveName();
            }

            views.add(new ApplicationView(
                    app.getId(),
                    studentName,
                    driveName,
                    app.getStatus()
            ));
        }

        return views;
    }

    public boolean hasApplied(Long studentId, Long driveId) {

        return applicationRepository.existsByStudentIdAndDriveId(studentId, driveId);

    }

    public List<ApplicationView> getApplicationsForStudent(Long studentId) {

        List<Application> applications =
                applicationRepository.findByStudentId(studentId);

        List<ApplicationView> views = new ArrayList<>();

        for (Application app : applications) {

            PlacementDrive drive =
                    placementDriveRepository
                            .findById(app.getDriveId())
                            .orElse(null);

            String driveName = "";
            String companyName = "";

            if (drive != null) {
                driveName = drive.getDriveName();
                companyName = drive.getCompanyName();
            }

            views.add(new ApplicationView(
                    app.getId(),
                    companyName,
                    driveName,
                    app.getStatus()
            ));
        }

        return views;
    }

}
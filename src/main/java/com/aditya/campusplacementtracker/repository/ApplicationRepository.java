package com.aditya.campusplacementtracker.repository;

import com.aditya.campusplacementtracker.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByStudentIdAndDriveId(Long studentId, Long driveId);

    List<Application> findByStatus(String status);

    List<Application> findByStudentId(Long studentId);

    long countByStatus(String status);
}
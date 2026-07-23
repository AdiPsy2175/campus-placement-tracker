package com.aditya.campusplacementtracker.repository;

import com.aditya.campusplacementtracker.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    long count();

    long countByPlacedTrue();

    List<Student> findByNameContainingIgnoreCase(String keyword);

    Page<Student> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
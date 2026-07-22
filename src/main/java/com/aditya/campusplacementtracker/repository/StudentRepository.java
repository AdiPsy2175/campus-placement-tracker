package com.aditya.campusplacementtracker.repository;

import com.aditya.campusplacementtracker.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
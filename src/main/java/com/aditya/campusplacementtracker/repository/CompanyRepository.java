package com.aditya.campusplacementtracker.repository;
import com.aditya.campusplacementtracker.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    long count();
}
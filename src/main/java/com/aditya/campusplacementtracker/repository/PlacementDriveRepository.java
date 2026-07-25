package com.aditya.campusplacementtracker.repository;

import com.aditya.campusplacementtracker.entity.PlacementDrive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlacementDriveRepository extends JpaRepository<PlacementDrive, Long> {
    List<PlacementDrive> findByDriveNameContainingIgnoreCase(String keyword);

    List<PlacementDrive> findByLocation(String location);

    List<PlacementDrive> findByPackageOfferedGreaterThanEqual(Double packageOffered);

}
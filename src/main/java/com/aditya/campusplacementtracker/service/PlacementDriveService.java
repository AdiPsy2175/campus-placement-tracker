package com.aditya.campusplacementtracker.service;

import com.aditya.campusplacementtracker.entity.PlacementDrive;
import com.aditya.campusplacementtracker.repository.PlacementDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacementDriveService {

    @Autowired
    private PlacementDriveRepository placementDriveRepository;

    public List<PlacementDrive> getAllDrives() {
        return placementDriveRepository.findAll();
    }

    public PlacementDrive saveDrive(PlacementDrive drive) {
        return placementDriveRepository.save(drive);
    }

    public PlacementDrive getDriveById(Long id) {
        return placementDriveRepository.findById(id).orElse(null);
    }

    public void deleteDrive(Long id) {
        placementDriveRepository.deleteById(id);
    }

    public long getDriveCount() {
        return placementDriveRepository.count();
    }

    public List<PlacementDrive> searchDrives(String keyword) {
        return placementDriveRepository.findByDriveNameContainingIgnoreCase(keyword);
    }

    public Page<PlacementDrive> getDrivesPage(int page, String sortBy) {

        Pageable pageable =
                PageRequest.of(page, 5, Sort.by(sortBy));

        return placementDriveRepository.findAll(pageable);
    }

    public List<PlacementDrive> getDrivesByLocation(String location) {
        return placementDriveRepository.findByLocation(location);
    }

    public List<PlacementDrive> getDrivesByPackage(Double packageOffered) {
        return placementDriveRepository
                .findByPackageOfferedGreaterThanEqual(packageOffered);
    }

}
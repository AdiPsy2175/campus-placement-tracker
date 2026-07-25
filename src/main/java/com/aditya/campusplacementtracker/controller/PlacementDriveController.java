package com.aditya.campusplacementtracker.controller;

import com.aditya.campusplacementtracker.entity.PlacementDrive;
import com.aditya.campusplacementtracker.service.PlacementDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlacementDriveController {

    @Autowired
    private PlacementDriveService placementDriveService;

    @GetMapping("/drives")
    public String viewDrives(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "driveName") String sortBy,
            Model model) {

        Page<PlacementDrive> drivePage =
                placementDriveService.getDrivesPage(page, sortBy);

        model.addAttribute("drives", drivePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", drivePage.getTotalPages());
        model.addAttribute("sortBy", sortBy);

        return "drives";
    }

    @GetMapping("/add-drive")
    public String showAddDriveForm(Model model) {
        model.addAttribute("drive", new PlacementDrive());
        return "add-drive";
    }

    @PostMapping("/save-drive")
    public String saveDrive(@ModelAttribute PlacementDrive drive) {
        placementDriveService.saveDrive(drive);
        return "redirect:/drives";
    }

    @GetMapping("/edit-drive/{id}")
    public String editDrive(@PathVariable Long id, Model model) {
        model.addAttribute("drive",
                placementDriveService.getDriveById(id));
        return "add-drive";
    }

    @GetMapping("/delete-drive/{id}")
    public String deleteDrive(@PathVariable Long id) {
        placementDriveService.deleteDrive(id);
        return "redirect:/drives";
    }

    @GetMapping("/drives/search")
    public String searchDrives(@RequestParam String keyword,
                               Model model) {

        model.addAttribute("drives",
                placementDriveService.searchDrives(keyword));

        return "drives";
    }

    @GetMapping("/drives/location")
    public String filterByLocation(
            @RequestParam String location,
            Model model) {

        model.addAttribute("drives",
                placementDriveService.getDrivesByLocation(location));

        return "drives";
    }

    @GetMapping("/drives/package")
    public String filterByPackage(
            @RequestParam Double packageOffered,
            Model model) {

        model.addAttribute("drives",
                placementDriveService.getDrivesByPackage(packageOffered));

        return "drives";
    }
}
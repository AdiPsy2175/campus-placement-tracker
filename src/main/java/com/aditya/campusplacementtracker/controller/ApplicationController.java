package com.aditya.campusplacementtracker.controller;

import com.aditya.campusplacementtracker.entity.Application;
import com.aditya.campusplacementtracker.service.ApplicationResult;
import com.aditya.campusplacementtracker.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;


    @GetMapping("/apply/{id}")
    public String showApplicationForm(@PathVariable Long id, Model model) {

        Application application = new Application();

        application.setDriveId(id);

        application.setStatus("Pending");

        model.addAttribute("application", application);

        return "apply";
    }

    @PostMapping("/apply")
    public String apply(@ModelAttribute Application application,
                        Model model) {

        ApplicationResult result =
                applicationService.saveApplication(application);

        if (!result.isSuccess()) {

            model.addAttribute("error", result.getMessage());

            model.addAttribute("application", application);

            return "apply";
        }

        return "redirect:/applications";
    }

    @GetMapping("/applications")
    public String viewApplications(
            @RequestParam(required = false, defaultValue = "") String keyword,
            Model model) {

        model.addAttribute("applications",
                applicationService.searchApplications(keyword));

        model.addAttribute("keyword", keyword);

        return "applications";
    }

    @GetMapping("/applications/status")
    public String filterApplications(
            @RequestParam String status,
            Model model) {

        model.addAttribute("applications",
                applicationService.getApplicationViewsByStatus(status));

        model.addAttribute("keyword", "");

        return "applications";
    }

    @GetMapping("/applications/update-status/{id}")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        applicationService.updateStatus(id, status);

        return "redirect:/applications";
    }
}
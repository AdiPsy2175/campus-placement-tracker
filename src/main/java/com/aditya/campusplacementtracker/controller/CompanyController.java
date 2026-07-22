package com.aditya.campusplacementtracker.controller;
import com.aditya.campusplacementtracker.entity.Company;
import com.aditya.campusplacementtracker.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public String viewCompanies(Model model) {

        model.addAttribute("companies", companyService.getAllCompanies());

        return "companies";
    }

    @GetMapping("/add-company")
    public String showCompanyForm(Model model) {

        model.addAttribute("company", new Company());

        return "add-company";
    }

    @PostMapping("/save-company")
    public String saveCompany(@ModelAttribute Company company) {

        companyService.saveCompany(company);

        return "redirect:/companies";
    }

    @GetMapping("/edit-company/{id}")
    public String editCompany(@PathVariable Long id, Model model) {

        Company company = companyService.getCompanyById(id);

        model.addAttribute("company", company);

        return "add-company";
    }

    @GetMapping("/delete-company/{id}")
    public String deleteCompany(@PathVariable Long id) {

        companyService.deleteCompany(id);

        return "redirect:/companies";
    }
}

package com.aditya.campusplacementtracker.service;
import com.aditya.campusplacementtracker.entity.Company;
import com.aditya.campusplacementtracker.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}

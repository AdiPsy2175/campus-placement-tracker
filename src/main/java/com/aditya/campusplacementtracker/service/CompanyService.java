package com.aditya.campusplacementtracker.service;

import com.aditya.campusplacementtracker.entity.Company;
import com.aditya.campusplacementtracker.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public long getCompanyCount() {
        return companyRepository.count();
    }

    public List<Company> searchCompanies(String keyword) {
        return companyRepository.findByCompanyNameContainingIgnoreCase(keyword);
    }

    public Page<Company> getCompaniesPage(int page, String sortBy) {

        Pageable pageable =
                PageRequest.of(page, 5, Sort.by(sortBy));

        return companyRepository.findAll(pageable);
    }
}

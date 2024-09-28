package com.works.firstjobapp.company.impl;

import com.works.firstjobapp.company.Company;
import com.works.firstjobapp.company.CompanyRepository;
import com.works.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {


    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {

        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean updateCompany(Long id,Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()){
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(updatedCompany.getDescription());
            companyToUpdate.setJobs(updatedCompany.getJobs());
            companyToUpdate.setName(updatedCompany.getName());
            companyRepository.save(companyToUpdate);
            return true;
        }

        return false;
    }

    @Override
    public void addCompany(Company newCompany) {
        companyRepository.save(newCompany);

    }

    @Override
    public Boolean deleteCompany(Long id) {
//        Optional<Company> companyDelete = companyRepository.findById(id);
         if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
         else{
            return false;

         }

    }
}

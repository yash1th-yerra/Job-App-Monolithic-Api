package com.works.firstjobapp.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();


    Company getCompanyById(Long id);

    Boolean updateCompany(Long id,Company updatedCompany);

    void addCompany(Company newCompany);

    Boolean deleteCompany(Long id);
}

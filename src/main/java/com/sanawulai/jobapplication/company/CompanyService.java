package com.sanawulai.jobapplication.company;

import java.util.List;

public interface CompanyService {
    //get all companies
    List<Company> getAllCompanies();

    boolean updateCompany(Company company,Long id);

    void createCompany(Company company);

    boolean deleteCompanyById(long id);

    Company getCompanyById(long id);



}

package com.sanawulai.jobapplication.company.imp;

import com.sanawulai.jobapplication.company.Company;
import com.sanawulai.jobapplication.company.CompanyRepository;
import com.sanawulai.jobapplication.company.CompanyService;
import com.sanawulai.jobapplication.job.Job;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyToUpdate.setJobs(company.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
        }else
            return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(long id) {
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
            }

    @Override
    public Company getCompanyById(long id) {
       return companyRepository.findById(id).orElse(null);
    }
}
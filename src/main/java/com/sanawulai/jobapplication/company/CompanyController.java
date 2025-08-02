package com.sanawulai.jobapplication.company;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController() {
    }

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable long id,
                                                @RequestBody Company company){
        companyService.updateCompany(company,id);
        return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
    }
}

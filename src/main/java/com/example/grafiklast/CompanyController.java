package com.example.grafiklast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/company/details")
    public Company getCompany(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return companyService.getCompanyDetails(name);
    }

    @PostMapping(value = "/company/create", consumes = "multipart/form-data")
    public String createCompany(@ModelAttribute Company company) throws InterruptedException, ExecutionException {
        return companyService.saveCompanyDetails(company);
    }

    @PutMapping("/company/update")
    public String updateCompany(@RequestBody Company company  ) throws InterruptedException, ExecutionException {
        return companyService.updateCompanyDetails(company);
    }

    @DeleteMapping("/company/delete")
    public String deleteCompany(@RequestParam String name){
        return companyService.deleteCompany(name);
    }
}
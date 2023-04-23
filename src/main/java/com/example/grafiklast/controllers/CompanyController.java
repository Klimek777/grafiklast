package com.example.grafiklast.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.grafiklast.Company;
import com.example.grafiklast.services.CompanyService;

import jakarta.validation.Valid;

import java.util.concurrent.ExecutionException;
@Controller
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/company/details")
    public Company getCompany(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return companyService.getCompanyDetails(name);
    }
    @PutMapping("/company/update")
    public String updateCompany(@RequestBody Company company) throws InterruptedException, ExecutionException {
        return companyService.updateCompanyDetails(company);
    }

    @DeleteMapping("/company/delete")
    public String deleteCompany(@RequestParam String name){
        return companyService.deleteCompany(name);
    }
}
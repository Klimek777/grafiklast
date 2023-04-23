package com.example.grafiklast.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.grafiklast.Company;
import com.example.grafiklast.services.CompanyService;

import jakarta.validation.Valid;

@Controller
public class RegisterController {

    private final CompanyService companyService;

    public RegisterController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/register")
    public String register(Company company, Model model, BindingResult result) {
        model.addAttribute("company", company);
        return "register";
    }

    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public String createCompany(Model model, @Valid Company company, BindingResult result) throws Exception {
        CompanyService companyService = new CompanyService();
        model.addAttribute("company", company);
        if(result.hasErrors()) {
            model.addAttribute("companyNameErrors", result.getFieldErrors("companyName"));
            model.addAttribute("emailErrors", result.getFieldErrors("email"));
            model.addAttribute("passwordErrors", result.getFieldErrors("password"));
            model.addAttribute("confirmPasswordErrors", result.getFieldErrors("confirmPassword"));
            return "register";
        }
        try {
            companyService.registerCompany(company);
        } catch(Exception e) {
            result.rejectValue("companyName", "company.name.error", "Nazwa firmy jest niepoprawna");
            model.addAttribute("company", company);
            model.addAttribute("errors", result.getAllErrors());
            return "redirect:/register";
        }
        return "redirect:/";
    }
}

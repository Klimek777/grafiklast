package com.example.grafiklast;

import javax.annotation.MatchesPattern;

import com.google.firebase.database.annotations.NotNull;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Company {

    private String companyId;

    @NotNull
    @NotEmpty(message = "This field cannot be empty!")
    private String companyName;

    @NotNull
    @NotEmpty(message = "This field cannot be empty!")
    private String email;
    
    @NotNull
    @NotEmpty(message = "This field cannot be empty!")
    @Size(min = 6, message = "Password must contain at least 6 signs!")
    @Size(max = 30, message = "Password cannot be longer than 30 signs!")
    private String password;

    @NotNull
    @NotEmpty(message = "This field cannot be empty!")
    // @AssertTrue(message = "Passwords do not match!")
    // public boolean isPasswordMatch() {
    //     if (password == null || confirmPassword == null) {
    //         return false;
    //     }
    //     return password.equals(confirmPassword);
    // }
    private String confirmPassword;

    public Company(String companyName, String password, String confirmPassword, String email) {
                this.companyId = "";
                this.companyName = companyName;
                this.password = password;
                this.confirmPassword = confirmPassword;
                this.email = email;
            }
        
            public String getCompanyName() {
                return companyName;
            }
        
            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getPassword() {
                return password;
            }
        
            public void setPassword(String password) {
                this.password = password;
            }

            public String getConfirmPassword() {
                return confirmPassword;
            }
        
            public void setConfirmPassword(String confirmPassword) {
                this.confirmPassword = confirmPassword;
            }
        
            public String getEmail() {
                return email;
            }
        
            public void setEmail(String email) {
                this.email = email;
            }

            public void setCompanyId(String documentId) {
                this.companyId = documentId;
            }
}
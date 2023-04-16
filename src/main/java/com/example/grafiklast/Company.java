package com.example.grafiklast;

import com.google.firebase.database.annotations.NotNull;

import jakarta.validation.constraints.NotEmpty;

public class Company {
    @NotNull
    @NotEmpty
    private String companyName;
    
    @NotNull
    @NotEmpty
    private String password;
    
    @NotNull
    @NotEmpty
    private String email;

    public Company(String companyName, String password, String email) {
                this.companyName = companyName;
                this.password = password;
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
        
            public String getEmail() {
                return email;
            }
        
            public void setEmail(String email) {
                this.email = email;
            }
}
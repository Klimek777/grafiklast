package com.example.grafiklast;

import com.google.firebase.database.annotations.NotNull;
import jakarta.validation.constraints.NotEmpty;

public class User {
    private String userId;

    private String companyName;
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String name;


    public User( String companyName,String email, String password, String name) {
        this.userId="";
        this.email=email;
        this.password = password;
        this.name = name;

    }

    public User() {
        // Empty constructor
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter i setter dla pola companyName
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public void setCity(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
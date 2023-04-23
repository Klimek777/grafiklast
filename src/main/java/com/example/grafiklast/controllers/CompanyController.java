package com.example.grafiklast.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.grafiklast.Company;
import com.example.grafiklast.services.CompanyService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import java.util.concurrent.ExecutionException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/company/details")
    public Company getCompany(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return companyService.getCompanyDetails(name);
    }

    @PostMapping(value = "/company/create", consumes = "multipart/form-data")
    public String createCompany(@ModelAttribute Company company) throws InterruptedException, ExecutionException {
        if(registerCompany(company) == "success")
            return "redirect:/login";
        return "login";
    }

    @PutMapping("/company/update")
    public String updateCompany(@RequestBody Company company  ) throws InterruptedException, ExecutionException {
        return companyService.updateCompanyDetails(company);
    }

    @DeleteMapping("/company/delete")
    public String deleteCompany(@RequestParam String name){
        return companyService.deleteCompany(name);
    }


    public static String getMd5Hash(String input) {
        try {
            // static getInstance() method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // calculating message digest of an input that return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // converting byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // converting message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        // for specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    
    public String registerCompany(Company company) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        String collectionName = "companies";

        String password = company.getPassword();

        // Tworzenie nowego dokumentu z unikalnym ID
        DocumentReference documentReference = dbFirestore.collection(collectionName).document();
        String documentId = documentReference.getId();

        // Ustawienie ID dokumentu w obiekcie company
        company.setCompanyId(documentId);

        // Dodanie użytkownika do Firebase Authentication
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(company.getEmail())
                .setPassword(password)
                .setDisplayName(company.getCompanyName());

        UserRecord userRecord = null;
        try {
            userRecord = firebaseAuth.createUser(request);
        } catch (FirebaseAuthException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Zapisanie obiektu company do Firestore
        company.setPassword(getMd5Hash(password));
        ApiFuture<WriteResult> writeResultApiFuture = documentReference.set(company);

        // Pobranie wyniku operacji zapisu
        WriteResult writeResult = writeResultApiFuture.get();
        return "success";
    }
}
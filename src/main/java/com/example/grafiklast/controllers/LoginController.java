package com.example.grafiklast.controllers;

import com.example.grafiklast.controllers.CompanyController;
import com.example.grafiklast.services.CompanyService;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.grafiklast.Company;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model, HttpSession session) {
        model.addAttribute("name", name);
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if(loggedIn != null && loggedIn) {
            return "redirect:/home";
        }
        else {
            return "login";
        }
    }

    @PostMapping(value = "/signing_in", consumes = "multipart/form-data")
    public String createCompany(@ModelAttribute Company company, HttpSession session) throws InterruptedException, FirebaseAuthException {
        if (signInUserOrCompany(company) == "success") {
            session.setAttribute("loggedIn", true);
            return "redirect:/home";
        }
        else {
            return "redirect:/login";
        }
    }

    public String signInUserOrCompany(Company company) throws FirebaseAuthException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        // Pobranie danych logowania z żądania
        String email = company.getEmail();
        String password = company.getPassword();

        // Autentykacja użytkownika przy użyciu Firebase Authentication
        FirebaseAuth auth = FirebaseAuth.getInstance();

        // Pobranie ID użytkownika
        // ApiFuture<QuerySnapshot> signingInUser =
        // dbFirestore.collection("companies").whereEqualTo("email", email).get();

        ApiFuture<QuerySnapshot> future = dbFirestore.collection("companies")
                .whereEqualTo("email", email)
                .get();

        try {
            QuerySnapshot querySnapshot = future.get();
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                String passwordHash = document.getString("password");
                String formPasswordHash = CompanyService.getMd5Hash(password);

                if(passwordHash.equals(formPasswordHash))  {
                    return "success";
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            Throwable cause = ((Throwable) e).getCause();
        }

        return "Dane logowania niepoprawne";
    }
}

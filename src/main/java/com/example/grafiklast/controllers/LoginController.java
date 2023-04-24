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
    public String loginCompany(@ModelAttribute Company company, HttpSession session) throws InterruptedException, FirebaseAuthException {
        if (signInUserOrCompany(company, session) == "success") {
            session.setAttribute("loggedIn", true);
            return "redirect:/home";
        }
        else {
            return "redirect:/login";
        }
    }

    public String signInUserOrCompany(Company company, HttpSession session) throws FirebaseAuthException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        // Pobranie danych logowania z żądania
        String email = company.getEmail();
        String password = company.getPassword();
        String companyName = company.getCompanyName();

        // Autentykacja użytkownika przy użyciu Firebase Authentication
        FirebaseAuth auth = FirebaseAuth.getInstance();

        // Pobranie ID użytkownika
        // ApiFuture<QuerySnapshot> signingInUser =
        // dbFirestore.collection("companies").whereEqualTo("email", email).get();

        //Pobranie ID uzytkownika z kolekcji "companies" czyli mengaerów
        ApiFuture<QuerySnapshot> futureCompanies = dbFirestore.collection("companies")
                .whereEqualTo("email", email)
                .get();
        // Pobranie ID użytkownika z kolekcji "users"
        ApiFuture<QuerySnapshot> futureUsers = dbFirestore.collection("users")
                .whereEqualTo("email", email)
                .get();

        try {
            QuerySnapshot querySnapshotCompanies = futureCompanies.get();
            QuerySnapshot querySnapshotUsers = futureUsers.get();

            //sprawdzenie dla kolekcji companies
            for (DocumentSnapshot document : querySnapshotCompanies.getDocuments()) {
                String passwordHash = document.getString("password");
                String formPasswordHash = CompanyService.getMd5Hash(password);

                if(passwordHash.equals(formPasswordHash))  {
                    session.setAttribute("companyName", document.getString("companyName"));
                    //jesli dziala na kolekcji comapnies i matchuje hasla dodaje atrybut menager
                    session.setAttribute("userType", "manager");
                    return "success";
                }
            }
            //sprawdzenie dla kolekcji users
            for (DocumentSnapshot document : querySnapshotUsers.getDocuments()) {
                String passwordHash = document.getString("password");
                String formPasswordHash = CompanyService.getMd5Hash(password);

                if (passwordHash.equals(formPasswordHash)) {
                    session.setAttribute("userName", document.getString("name"));
                    //jesli dziala po kolekcji user i matchuje hasla dodaje atrybut user
                    session.setAttribute("userType", "user");
                    return "success";
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            Throwable cause = ((Throwable) e).getCause();
        }

        return "Dane logowania niepoprawne";
    }
}

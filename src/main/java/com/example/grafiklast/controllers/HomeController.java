package com.example.grafiklast.controllers;

import com.example.grafiklast.User;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class HomeController {
    private Firestore firestore;
    @GetMapping("/")
    public String defaultPage(HttpSession session, Model model) throws FirebaseAuthException {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

        if(loggedIn != null && loggedIn) {
            return "redirect:/home";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) throws ExecutionException, InterruptedException {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        String userType = (String) session.getAttribute("userType");
        String userName= (String) session.getAttribute("userName");
        String companyName = (String) session.getAttribute("companyName");

        if(loggedIn != null && loggedIn && userType.equals("manager")) {
            model.addAttribute("userEmail", companyName);
            List<User> userList = findByCompanyName(companyName);
            model.addAttribute("userList", userList);
            System.out.println(userList);
            return "home_manager";
        } else if (loggedIn != null && loggedIn && userType.equals("user")) {
            model.addAttribute("userEmail", userName);
            return "home_worker";
        } else {
            System.out.println(userType);
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("loggedIn", false);
        return "redirect:/login";
    }



    public List<User> findByCompanyName(String companyName) throws InterruptedException, ExecutionException {
        firestore = FirestoreClient.getFirestore();

        List<User> userList = new ArrayList<>(); // Lista, która będzie przechowywać obiekty User
        CollectionReference usersCollection = firestore.collection("users"); // Referencja do kolekcji "users" w bazie Firebase

        // Tworzenie zapytania do bazy Firebase, które filtruje dokumenty na podstawie nazwy firmy
        Query query = usersCollection.whereEqualTo("companyName", companyName);

        // Wykonanie zapytania i oczekiwanie na wynik
        QuerySnapshot querySnapshot = query.get().get();

        // Iteracja po dokumentach wynikowych i tworzenie obiektów User na podstawie danych z dokumentów
        for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
            User user = documentSnapshot.toObject(User.class); // Konwersja dokumentu na obiekt User
            userList.add(user); // Dodanie obiektu User do listy
        }

        return userList; // Zwrócenie listy obiektów User
    }

}

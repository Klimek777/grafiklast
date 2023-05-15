package com.example.grafiklast.controllers;

import com.example.grafiklast.User;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class CalendarController {
    private Firestore firestore;
    @GetMapping("/calendar")
    public String calendarPage(HttpSession session, Model model) throws FirebaseAuthException {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

        if(loggedIn != null && loggedIn) {
            return "calendar";
        }
        else {
            return "redirect:/login";
        }
    }
}

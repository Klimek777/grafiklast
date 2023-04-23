package com.example.grafiklast.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/")
    public String defaultPage(HttpSession session) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if(loggedIn != null && loggedIn) {
            return "redirect:/home";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/home")
    public String homePage(HttpSession session) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        if(loggedIn != null && loggedIn) {
            return "home_manager";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("loggedIn", false);
        return "redirect:/login";
    }
}

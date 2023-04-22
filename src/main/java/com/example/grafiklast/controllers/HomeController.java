package com.example.grafiklast.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model, HttpSession session) {
        model.addAttribute("name", name);
        if(session.isNew()){
            if((Boolean) session.getAttribute("loggedIn") == true) {
                return "home_manager";
            }
            else {
                return "redirect:/login";
            }
        }
        else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("loggedIn", false);
        return "redirect:/login";
    }
}

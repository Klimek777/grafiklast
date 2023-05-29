package com.example.grafiklast.controllers;

import com.example.grafiklast.CalendarEvent;
import com.example.grafiklast.Event;
import com.example.grafiklast.services.CalendarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;

import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;


@Controller
public class CalendarController {
    
    @GetMapping("/calendar/manager/{userId}")
    public String calendarManagerPage(@PathVariable("userId") String userId, HttpSession session, Model model) throws FirebaseAuthException, InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

        model.addAttribute("userId", userId);

        ApiFuture<QuerySnapshot> futureUsers = firestore.collection("users")
                .whereEqualTo("userId", userId)
                .get();
            
        QuerySnapshot querySnapshot = futureUsers.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        if(!documents.isEmpty()) {
            DocumentSnapshot firstDocument = documents.get(0);
            model.addAttribute("userName", firstDocument.get("name"));
        }

        if(loggedIn != null && loggedIn && session.getAttribute("userType").equals("manager")) {
            return "calendar_manager";
        }
        else if(loggedIn != null && loggedIn) {
            return "redirect:/home";
        }
        else {
            return "redirect:/login";
        }
    }

    @PostMapping("/calendar/manager/{userId}/save")
    @ResponseBody
    public String calendarManagerEventsSave(@RequestBody String localStorageData, @PathVariable("userId") String userId) {
        Firestore firestore = FirestoreClient.getFirestore();

        String localStorageDataJSON = URLDecoder.decode(localStorageData.substring(5));

        ObjectMapper objectMapper = new ObjectMapper();
        CalendarService calendarService = new CalendarService();
        
        try {
            // konwert JSONa przysłanego z JavaScriptu na obiekt klasy CalendarEvent
            CalendarEvent[] calendarEvents = objectMapper.readValue(localStorageDataJSON, CalendarEvent[].class);

            // // dotarcie do kaedgo z eventów
            // for (CalendarEvent calendarEvent : calendarEvents) {
            //     Event[] dailyCalendarEvents = calendarEvent.getEvents();
            //     String date = calendarEvent.getYear()+'-'+calendarEvent.getMonth()+'-'+calendarEvent.getDay();
            //     for (Event dailyCalendarEvent : dailyCalendarEvents) {
            //         // wywołanie funkcji zapisującej event do bazy
            calendarService.saveEvents(userId, localStorageDataJSON);
            //     }
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Success!";
    }

    @PostMapping("/calendar/manager/{userId}/read")
    @ResponseBody
    public String calendarManagerEventsReadFromDatabase(@RequestBody String localStorageData, @PathVariable("userId") String userId) throws Exception {
        CalendarService calendarService = new CalendarService();

        String userEventsData = calendarService.getEvents(userId);

        return userEventsData;
    }

    @PostMapping("/calendar/manager/{userId}/all/read")
    @ResponseBody
    public String calendarManagerEventsReadAllWorkersFromDatabase(HttpSession session, @RequestBody String localStorageData, @PathVariable("userId") String userId) throws Exception {
        CalendarService calendarService = new CalendarService();

        return calendarService.getEventsAllWorkers(session).toString();
    }

    @PostMapping("/calendar/worker/{userId}/read")
    @ResponseBody
    public String calendarWorkerEventsReadFromDatabase(@RequestBody String localStorageData, @PathVariable("userId") String userId) throws Exception {
        CalendarService calendarService = new CalendarService();

        String userEventsData = calendarService.getEvents(userId);

        return userEventsData;
    }

    @PostMapping("/calendar/worker/{userId}/all/read")
    @ResponseBody
    public String calendarWorkerEventsReadAllWorkersFromDatabase(HttpSession session, @RequestBody String localStorageData, @PathVariable("userId") String userId) throws Exception {
        CalendarService calendarService = new CalendarService();

        return calendarService.getEventsAllWorkers(session).toString();
    }

    @PostMapping("/calendar/disposition/{userId}/save")
    @ResponseBody
    public String calendarDispositionSave(@RequestBody String dispositionData, @PathVariable("userId") String userId) {

        String dispositionDataJSON = URLDecoder.decode(dispositionData.substring(5));

        ObjectMapper objectMapper = new ObjectMapper();
        CalendarService calendarService = new CalendarService();
        
        try {
            // konwert JSONa przysłanego z JavaScriptu na obiekt klasy CalendarEvent
            //CalendarEvent[] calendarEvents = objectMapper.readValue(dispositionDataJSON, CalendarEvent[].class);

            // // dotarcie do kaedgo z eventów
            // for (CalendarEvent calendarEvent : calendarEvents) {
            //     Event[] dailyCalendarEvents = calendarEvent.getEvents();
            //     String date = calendarEvent.getYear()+'-'+calendarEvent.getMonth()+'-'+calendarEvent.getDay();
            //     for (Event dailyCalendarEvent : dailyCalendarEvents) {
            //         // wywołanie funkcji zapisującej event do bazy
            calendarService.saveDisposition(userId, dispositionDataJSON);
            //     }
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Success!";
    }

    @RequestMapping(value = { "/calendar/disposition/{userId}/read", "/calendar/manager/{userId}/disposition/read" }, method = RequestMethod.POST )
    @ResponseBody
    public String calendarDispositionReadFromDatabase(@RequestBody String localStorageData, @PathVariable("userId") String userId) throws Exception {
        CalendarService calendarService = new CalendarService();

        String userEventsData = calendarService.getDisposition(userId);

        return userEventsData;
    }
    

    // public String calendarManagerSavePage(@PathVariable("userId") String userId, HttpSession session, Model model) throws FirebaseAuthException, InterruptedException, ExecutionException {
    //     Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        

    //     if(loggedIn != null && loggedIn && session.getAttribute("userType").equals("manager")) {
    //         return "redirect:/home";
    //     }
    //     else {
    //         return "redirect:/login";
    //     }
    // }

    @GetMapping("/calendar/worker/{userId}")
    public String calendarWorkerPage(@PathVariable("userId") String userId, HttpSession session, Model model) throws FirebaseAuthException, InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

        model.addAttribute("userId", userId);

        ApiFuture<QuerySnapshot> futureUsers = firestore.collection("users")
                .whereEqualTo("userId", userId)
                .get();
            
        QuerySnapshot querySnapshot = futureUsers.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        if(!documents.isEmpty()) {
            DocumentSnapshot firstDocument = documents.get(0);
            model.addAttribute("userName", firstDocument.get("name"));
        }

        if(loggedIn != null && loggedIn && session.getAttribute("userType").equals("manager")) {
            return "calendar_manager";
        }
        else if(loggedIn != null && loggedIn && session.getAttribute("userType").equals("user")) {
            return "calendar_worker";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/calendar/disposition/{userId}")
    public String calendarWorkerDispositionPage(@PathVariable("userId") String userId, HttpSession session, Model model) throws FirebaseAuthException, InterruptedException, ExecutionException {
        Firestore firestore = FirestoreClient.getFirestore();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

        model.addAttribute("userId", userId);

        ApiFuture<QuerySnapshot> futureUsers = firestore.collection("users")
                .whereEqualTo("userId", userId)
                .get();
            
        QuerySnapshot querySnapshot = futureUsers.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        if(!documents.isEmpty()) {
            DocumentSnapshot firstDocument = documents.get(0);
            model.addAttribute("userName", firstDocument.get("name"));
        }

        if(loggedIn != null && loggedIn && session.getAttribute("userType").equals("user")) {
            return "calendar_disposition";
        }
        else if(loggedIn != null && loggedIn) {
            return "redirect:/home";
        }
        else {
            return "redirect:/login";
        }
    }
}

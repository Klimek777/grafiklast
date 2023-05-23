package com.example.grafiklast.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.grafiklast.CalendarEvent;
import com.example.grafiklast.Company;
import com.example.grafiklast.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;

public class CalendarService {
    // public String saveEvent(String userId, String date, Event calendarEvent) throws Exception {
    //     calendarEvent.setDate(date);
    //     Firestore dbFirestore = FirestoreClient.getFirestore();
    //     FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    //     DocumentReference documentReference = dbFirestore.collection("events").document(userId).collection(date)
    //             .document(calendarEvent.getTitle());
    //     documentReference.set(calendarEvent);
    //     return "success";
    // }

    public String saveEvent(String userId, String eventsJSON) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    
        DocumentReference documentReference = dbFirestore.collection("events").document(userId);
        Map<String, Object> eventMap = new HashMap<>();
        eventMap.put("eventsJSON", eventsJSON);
        documentReference.set(eventMap).get();
    
        return "success";
    }

    public String getEvents(String userId) throws Exception {
        Firestore dbFirestore = FirestoreClient.getFirestore();
    
        DocumentReference documentReference = dbFirestore.collection("events").document(userId);
        ApiFuture<DocumentSnapshot> documentSnapshot = documentReference.get();
    
        try {
            DocumentSnapshot snapshot = documentSnapshot.get();
            if (snapshot.exists()) {
                Map<String, Object> eventData = snapshot.getData();
                String eventsJSON = (String) eventData.get("eventsJSON");
                return eventsJSON;
            } else {
                throw new RuntimeException("No events found for the given user.");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // Handle error retrieving document
            throw new RuntimeException("Failed to retrieve events from Firestore.");
        }
    }    

}

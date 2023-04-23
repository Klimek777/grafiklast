package com.example.grafiklast.controllers;

import com.google.api.Http;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.grafiklast.User;
import com.example.grafiklast.services.UserService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/details")
    public User getUser(@RequestParam String name, HttpSession session) throws InterruptedException, ExecutionException{
        String companyName =(String) session.getAttribute("companyName");
        return userService.getUserDetails(name);
    }
    //TODO: do poprawienia zwracanie widoku, moze pop up succes i error?
    @PostMapping(value = "/add_people", consumes = "multipart/form-data")
    public String createUser(@ModelAttribute User user, HttpSession session) throws InterruptedException, ExecutionException {
        if(registerUser(user, session).equals("success")){
            return "add_people";
        }
        return "add_people";
    }

    @PutMapping("/user/update")
    public String updateUser(@RequestBody User user  ) throws InterruptedException, ExecutionException {
        return userService.updateUserDetails(user);
    }

    @DeleteMapping("/user/delete")
    public String deleteUser(@RequestParam String name){
        return userService.deleteUser(name);
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
    public String registerUser(User user, HttpSession session) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


        String collectionName = "users";

        String password = user.getPassword();

        // Tworzenie nowego dokumentu z unikalnym ID
        DocumentReference documentReference = dbFirestore.collection(collectionName).document();
        String documentId = documentReference.getId();

        // Ustawienie ID dokumentu w obiekcie company
        user.setUserId(documentId);

        //Ustawienie companyName w obiekcie user
        String companyName = (String) session.getAttribute("companyName");
        System.out.println(companyName);
        user.setCompanyName(companyName);

        // Dodanie użytkownika do Firebase Authentication
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmail())
                .setPassword(password)
                .setDisplayName(user.getName()
                )
                ;

        UserRecord userRecord = null;
        try {
            userRecord = firebaseAuth.createUser(request);
        } catch (FirebaseAuthException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Zapisanie obiektu company do Firestore
        user.setPassword(getMd5Hash(password));
        ApiFuture<WriteResult> writeResultApiFuture = documentReference.set(user);

        // Pobranie wyniku operacji zapisu
        WriteResult writeResult = writeResultApiFuture.get();
        return "success";
    }
}
package com.example.grafiklast.services;

import com.example.grafiklast.Company;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//CRUD operations
@Service
public class CompanyService {

    public static final String COL_NAME = "companies";

    public Company getCompanyDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Company company = null;

        if (document.exists()) {
            company = document.toObject(Company.class);
            return company;
        } else {
            return null;
        }
    }

    public String updateCompanyDetails(Company company) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> update = dbFirestore.collection(COL_NAME)
                .document(company.getCompanyName())
                .update("password", company.getPassword(), "email", company.getEmail());
        return update.get().getUpdateTime().toString();
    }

    public String deleteCompany(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "Document with Company ID " + name + " has been deleted";
    }

    public String registerCompany(Company company) throws Exception {
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
        if(1==1)
        {    
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(company.getEmail())
                    .setPassword(password)
                    .setDisplayName(company.getCompanyName());

            UserRecord userRecord = null;
            if(!company.getPassword().equals(company.getConfirmPassword())) {
                throw new Exception("Podane hasła nie są zgodne");
            }
            try {
                userRecord = firebaseAuth.createUser(request);
            } catch (FirebaseAuthException e) {
                throw e;
            }

            // Zapisanie obiektu company do Firestore
            company.setPassword(getMd5Hash(password));
            documentReference.set(company);
        }
        return "success";
        // else if(company.getPassword() != company.getConfirmPassword()) {
        //     return "Hasło musić mieć co najmniej 6 znaków";
        // }
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

}
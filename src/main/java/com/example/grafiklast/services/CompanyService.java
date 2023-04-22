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

}
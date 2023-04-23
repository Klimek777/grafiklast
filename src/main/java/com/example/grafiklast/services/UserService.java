package com.example.grafiklast.services;

import com.example.grafiklast.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class UserService {

    public static final String COL_NAME="users";



    public User getUserDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        User user = null;

        if(document.exists()) {
            user = document.toObject(User.class);
            return user;
        }else {
            return null;
        }
    }

    public String updateUserDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> update = dbFirestore.collection(COL_NAME)
                .document(user.getName())
                .update("email", user.getEmail(), "password", user.getPassword());
        return update.get().getUpdateTime().toString();
    }

    public String deleteUser(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "Document with User ID "+name+" has been deleted";
    }

}
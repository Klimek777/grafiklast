package com.example.grafiklast;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseOptions.Builder;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class FBInitialize {

    @PostConstruct
    public void initialize() {
        try {
            ClassPathResource serviceAccount =
            new ClassPathResource("serviceAccountKey.json");
            
            FirebaseOptions options = new Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
            .setDatabaseUrl("https://shiftmate-f7d4f-default-rtdb.europe-west1.firebasedatabase.app")
            .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
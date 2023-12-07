package app.casinoroyale.Controller.FirebaseControllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.auth.Credentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.auth.oauth2.GoogleCredentials.*;

import static com.google.auth.oauth2.ServiceAccountCredentials.fromStream;

/**
 *
 * @author JavaFX App
 */
public class FirestoreContext {

    public Firestore firebase() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(fromStream(getClass().getResourceAsStream("/app/Firebase/key.json")))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return FirestoreClient.getFirestore();
    }

    /*
    public Firestore firebase() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("key.json");
            ServiceAccountCredentials credentials = ServiceAccountCredentials.fromStream(inputStream);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return FirestoreClient.getFirestore();
    }
    */


}
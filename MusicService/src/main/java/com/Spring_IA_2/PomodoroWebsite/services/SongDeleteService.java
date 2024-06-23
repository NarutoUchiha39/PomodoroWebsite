package com.Spring_IA_2.PomodoroWebsite.services;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SongDeleteService {
    private Storage storage;
    private Firestore firestore;

    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
            ClassPathResource serviceAccount = new ClassPathResource("PomodoroWebsite.json");
            storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                    .setProjectId("pomodorowebsite-cab09.appspot.com")
                    .build()
                    .getService();
            firestore = FirestoreClient.getFirestore();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    

    public void deleteTest(String filename) {
        try {
            CollectionReference reference = firestore.collection("Songs");

            // Retrieve all documents in the collection
            ApiFuture<DocumentSnapshot> future = reference.document(filename).get();
            DocumentSnapshot documentSnapshot  = future.get();

            if(documentSnapshot.exists()){

                DocumentReference documentReference = reference.document(filename);
                documentReference.delete();

            }
        //     QuerySnapshot querySnapshot = future.get();

        //     // Iterate through each document
        //     for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
        //         // Assuming your file name field is called "url"
        //         String url = document.getString("url");

        //         // Compare the filename with the filename to match
        //         if (url != null && url.equals(filename)) {
        //             System.out.println(document.getString("url"));

        //             // Get the reference to the document to delete
        //             DocumentReference docRef = reference.document(document.getId());
                    
        //             // Delete the document
        //             ApiFuture<WriteResult> deleteFuture = docRef.delete();
                    
        //             // Wait for the delete operation to complete
        //             deleteFuture.get();
                    
        //             System.out.println("Document with url " + url + " deleted successfully.");
        //         }
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
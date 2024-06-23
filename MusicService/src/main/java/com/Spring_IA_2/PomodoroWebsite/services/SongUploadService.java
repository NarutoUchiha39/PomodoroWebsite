package com.Spring_IA_2.PomodoroWebsite.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
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
public class SongUploadService {
    private Storage storage;


    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
            ClassPathResource serviceAccount = new ClassPathResource("PomodoroWebsite.json");
            storage = StorageOptions.newBuilder().
                    setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).
                    setProjectId("pomodorowebsite-cab09.appspot.com").build().getService();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String saveTestUpdate(MultipartFile file) throws IOException {

        String imageName = file.getOriginalFilename();
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", imageName);
        BlobId blobId = BlobId.of("pomodorowebsite-cab09.appspot.com", imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(map)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getInputStream());

        return imageName;

    }
    public String saveTest(MultipartFile file) throws IOException {
        String imageName = generateFileName(file.getOriginalFilename());
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", imageName);
        BlobId blobId = BlobId.of("pomodorowebsite-cab09.appspot.com", imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(map)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getInputStream());


        return imageName;
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "." + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }


}

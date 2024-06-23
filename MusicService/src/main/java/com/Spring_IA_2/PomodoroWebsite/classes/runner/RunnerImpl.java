package com.Spring_IA_2.PomodoroWebsite.classes.runner;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

import com.Spring_IA_2.PomodoroWebsite.configuration.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class RunnerImpl implements CommandLineRunner{


    private Config config;
    @Autowired
    RunnerImpl(Config config){
        this.config = config;
    }

    @Override
    public void run(String... args) throws Exception {
        ClassLoader classLoader = RunnerImpl.class.getClassLoader();
        File Credentials = new File(Objects.requireNonNull(classLoader.getResource("PomodoroWebsite.json")).getFile());
        FileInputStream serviceAccount = new FileInputStream(Credentials.getAbsolutePath());
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl(config.getUrl()).setStorageBucket("pomodorowebsite-5a017.appspot.com").build();
        FirebaseApp.initializeApp(options);
    }

    
    
}

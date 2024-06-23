package com.Spring_IA_2.PomodoroWebsite.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.Spring_IA_2.PomodoroWebsite.classes.Music.Music;
import com.Spring_IA_2.PomodoroWebsite.classes.Music.MusicForm;
import com.Spring_IA_2.PomodoroWebsite.classes.ReturnMessage;
import com.Spring_IA_2.PomodoroWebsite.services.SongUploadService;
import com.Spring_IA_2.PomodoroWebsite.services.SongsCRUDService;
import com.Spring_IA_2.PomodoroWebsite.services.SongDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping(path = "/api")
public class Api {
    SongUploadService songUploadService;
    SongsCRUDService songsCRUDService;
    SongDeleteService songDeleteService;

    @Autowired
    public Api(SongUploadService songUploadService, SongsCRUDService songsCRUDService, SongDeleteService songDeleteService){
            this.songUploadService = songUploadService;
            this.songsCRUDService = songsCRUDService;
            this.songDeleteService = songDeleteService;
    }

    @RequestMapping(path ="/uploadSongs",method =RequestMethod.POST )
    public ResponseEntity<ReturnMessage> uploadSongs(@ModelAttribute("MusicForm")MusicForm file) throws IOException, ExecutionException, InterruptedException {
        if(file.getFile().isEmpty()){
            return new ResponseEntity<>(new ReturnMessage("Empty Files not allowed :'("),HttpStatus.valueOf(401));
        }
        System.out.println(file.getFile().getContentType());
        if(!file.getFile().getContentType().equals("audio/mpeg")){
            return new ResponseEntity<>(new ReturnMessage("Only mp3 files are allowed :'("),HttpStatus.valueOf(402));
        }

        String FileName = songUploadService.saveTest(file.getFile());
        System.out.println(FileName);
        file.setUrl(FileName);
        songsCRUDService.CreateSongs(new Music(file.getTitle(),file.getArtist(),file.getCredits(),file.getUrl()));
        return new ResponseEntity<>(new ReturnMessage("Uploaded Successfully"),HttpStatus.valueOf(200));
    }

    @RequestMapping(path = "/editSongs/{id}",method = RequestMethod.POST)
    public ResponseEntity<ReturnMessage>updateSongs(@PathVariable("id") String id, @ModelAttribute("MusicForm")MusicForm musicForm) throws IOException, ExecutionException, InterruptedException {
        String FileName = id;
        if(musicForm.getFile() != null){

            if(!musicForm.getFile().getContentType().equals("audio/mpeg")){
                return new ResponseEntity<>(new ReturnMessage("Only mp3 files are allowed :'("),HttpStatus.valueOf(402));
            }
            FileName = songUploadService.saveTestUpdate(musicForm.getFile());
        }

        musicForm.setUrl(FileName);
        songsCRUDService.updateSong(new Music(musicForm.getTitle(),musicForm.getArtist(),musicForm.getCredits(),musicForm.getUrl()));
        return new ResponseEntity<>(new ReturnMessage("Edited Song Successfully"),HttpStatus.OK);

    }
    
    @RequestMapping(path = "/deleteSongs/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> deleteSongs(@PathVariable("id") String id) throws IOException {
        songDeleteService.deleteTest(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    @RequestMapping(path = "/getSongsList",method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Music>> getSongs() throws ExecutionException, InterruptedException {
       ArrayList<Music> music = songsCRUDService.getSongs();
       return new ResponseEntity<>(music, HttpStatus.OK);
    }

    @RequestMapping(path="/downloadSong/{songName}",method = RequestMethod.GET)
    public ResponseEntity<ReturnMessage> downloadSong(@PathVariable("songName") String songName) throws IOException {

        String songUrl = "https://firebasestorage.googleapis.com/v0/b/pomodorowebsite-cab09.appspot.com/o/"+songName+"?alt=media&token="+songName;

        try {
            URL url = new URL(songUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set up the connection
            connection.setRequestMethod("GET");

            // Check if the response code indicates success
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Get the input stream containing the binary data
                try (InputStream inputStream = connection.getInputStream()) {
                    // Save the binary data to a file (you might need to handle file naming appropriately)
                    try (FileOutputStream fileOutputStream = new FileOutputStream(songName)) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;

                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }

                        System.out.println("Song downloaded successfully.");
                    }
                }
            } else {
                return new ResponseEntity<>(new ReturnMessage("Error in downloading Song "+connection.getResponseCode()),HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            return new ResponseEntity<>(new ReturnMessage("Error in downloading song :'D"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new ReturnMessage("Song downloaded successfully :D"),HttpStatus.OK);
    }
}
package com.Spring_IA_2.PomodoroWebsite.classes.Music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicForm{
    private String title;
    private String artist;
    private String credits;
    private String url;
    private MultipartFile file;
}

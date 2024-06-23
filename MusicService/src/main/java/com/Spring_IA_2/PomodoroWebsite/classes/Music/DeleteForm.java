package com.Spring_IA_2.PomodoroWebsite.classes.Music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteForm{
    private String title;

    public String getFilename() {
        return title;
    }
}

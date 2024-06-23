package com.Authentication.Auth.TemplateClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    private String title;
    private String artist;
    private String credits;
    private String url;

}

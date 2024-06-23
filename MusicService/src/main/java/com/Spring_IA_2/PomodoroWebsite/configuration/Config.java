package com.Spring_IA_2.PomodoroWebsite.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:Firebase.json",factory = JsonPropertySourceFactory.class)
@ConfigurationProperties
public class Config {
    private String url;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

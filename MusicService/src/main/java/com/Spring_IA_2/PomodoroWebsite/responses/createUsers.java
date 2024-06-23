package com.Spring_IA_2.PomodoroWebsite.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class createUsers {

    private String email;
    private String name;
    private String role;

    private String message;
}

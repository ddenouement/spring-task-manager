package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private int roleId;
    private int professionId;
    private String localeName;

    public User() {

    }
}


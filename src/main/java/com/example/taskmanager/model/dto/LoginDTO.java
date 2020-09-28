package com.example.taskmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class LoginDTO {
    private String login;
    private String password;
}

package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "firstname", nullable = false)
    @Size(max = 64, message = "First Name should not exceed 64 characters")
    private String firstName;

    @Column(name = "lastname", nullable = false)
    @Size(max = 64, message = "Last Name should not exceed 64 characters")
    private String lastName;


    @Column(name = "email", nullable = false)
    @Email(message = "Email must be valid")
    private String email;


    @Column(name = "login", nullable = false)
    @Size(max = 64, message = "Login should not exceed 64 characters")
    private String login;


    @Column(name = "password", nullable = false)
    @Size(min=8, message = "Password must be minimum of 8 characters")
    private String password;
/*
    @Column(name = "id_role", nullable = false)
    private int roleId;

    @Column(name = "id_profession", nullable = true)
    private int professionId;*/


    @Column(name = "locale", nullable = true)
    private String localeName;

//mappings

    @Column(name="role")
    @Enumerated(EnumType.STRING )
    private Role role;

    @OneToMany(mappedBy="user")
    private Set<UserActivity> tasks= new HashSet<>();

    @OneToMany(mappedBy="user")
    private Set<Request> requests = new HashSet<>();

    public User() {

    }
}


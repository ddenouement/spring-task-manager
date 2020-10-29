package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "firstname", nullable = false)
    @Size(max = 64, message = "First Name should not exceed 64 characters")
    private String firstName;

    @Column(name = "lastname", nullable = false)
    @Size(max = 64, message = "Last Name should not exceed 64 characters")
    private String lastName;


    @Column(name = "email", nullable = false, unique=true)
    @Email(message = "Email must be valid")
    private String email;


    @Column(name = "login", nullable = false, unique=true)
    @Size(max = 64, message = "Login should not exceed 64 characters")
    private String login;


    @Column(name = "password", nullable = false)
    @Size(min=8, message = "Password must be minimum of 8 characters")
    private String password;

    @Column(name = "locale", nullable = true)
    private String localeName;

//mappings

    @Column(name="role")
    @Enumerated(EnumType.STRING )
    private Role role;


    @OneToMany(mappedBy="user")
    private Set<UserActivity> tasks= new HashSet<>();


    public void setRoleId(int id){
        if( id==1) role = Role.ADMIN;
        else role = Role.USER;
    }

    public String getRole(){
       return role.getName();
    }
    public User() {

    }
}


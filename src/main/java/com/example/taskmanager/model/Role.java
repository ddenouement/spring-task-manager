package com.example.taskmanager.model;


public enum  Role {
  ADMIN, USER;


    public String getName() {
        return name().toUpperCase();
    }

}

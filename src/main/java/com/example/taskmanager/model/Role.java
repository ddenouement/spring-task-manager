package com.example.taskmanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public enum  Role {
  ADMIN, USER;


    public String getName() {
        return name().toUpperCase();
    }

}

package com.example.taskmanager.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public enum  Status {

    CREATED, REJECTED, ACCEPTED;

    public String getName() {
        return name().toUpperCase();
    }
}

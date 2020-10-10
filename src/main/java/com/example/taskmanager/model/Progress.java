package com.example.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
public enum  Progress {
    REQUESTED, ASSIGNED, FINISHED, CANCELLED;

    public String getName() {
        return name().toUpperCase();
    }
}
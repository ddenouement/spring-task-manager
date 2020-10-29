package com.example.taskmanager.model;
public enum  Status {

    REQUESTED_ADD, REQUESTED_REMOVE, ASSIGNED, FINISHED, CANCELLED, REJECTED;

    public String getName() {
        return name().toUpperCase();
    }
}

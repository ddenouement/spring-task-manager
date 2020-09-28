package com.example.taskmanager.model;


public enum Role {
    ADMIN, USER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toUpperCase();
    }

}

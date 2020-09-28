package com.example.taskmanager.dao;

import com.example.taskmanager.model.User;

public interface IUserDao {
User findByUsername(String login);
}

package com.example.taskmanager.service;

import com.example.taskmanager.model.User;
import com.example.taskmanager.model.dto.LoginDTO;

public interface  IAuthService {
      User register(User u);
      User login(LoginDTO u);
      User logout(User u);
}

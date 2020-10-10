package com.example.taskmanager.service;

import com.example.taskmanager.model.User;
import com.example.taskmanager.model.dto.LoginDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface  IAuthService {
      User register(User u);
}

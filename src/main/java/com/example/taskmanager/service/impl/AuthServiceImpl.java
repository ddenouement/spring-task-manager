package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.IUserRepository;
import com.example.taskmanager.model.User;
import com.example.taskmanager.model.dto.LoginDTO;
import com.example.taskmanager.service.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    IUserRepository repository;
    @Override
    public User register(User u) {
        u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        repository.save(u);
        return u;
    }}
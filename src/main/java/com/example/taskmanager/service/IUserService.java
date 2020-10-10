package com.example.taskmanager.service;

import com.example.taskmanager.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    Long count();

    User findUserByLogin(String login) throws UsernameNotFoundException;


    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<User> findAllUsers();

}

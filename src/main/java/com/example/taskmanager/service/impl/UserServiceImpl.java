package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.IUserRepository;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, IUserService {
    @Autowired
    private IUserRepository userDao;


    public User findUserByLogin(String login) throws UsernameNotFoundException {
        User user = userDao.findByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException("No such login");
        }
        return user;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByLogin(username);
        if(user == null){
            throw new UsernameNotFoundException("No such login");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }


    public List<User> findAllUsers() {
        Iterable<User> it = userDao.findAll();
        List <User> users = new ArrayList();
        it.forEach(users::add);
        return users;
    }

    public Long count() {
        return userDao.count();
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" +   user.getRole().getName()));
        return authorities;
    }





}

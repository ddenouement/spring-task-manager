package com.example.taskmanager.dao;

import com.example.taskmanager.model.User;
import com.example.taskmanager.service.IUserService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/*
public class UserRepositoryImpl{// implements IUserRepository {//implements IUserRepository   {
    static ArrayList<User> u = new ArrayList<>();

    static {
        u.add(new User(1, "julia", "aleksandrova", "julia@gmail.com", "user",  new BCryptPasswordEncoder().encode("password"), 1, new Profession(1, "designer"), "en"));
        u.add(new User(2, "whitney", "houston", "whi@gmail.com", "whi",  new BCryptPasswordEncoder().encode("password"), 1,  new Profession(2, "artist"), "en"));
        u.add(new User(3, "coco", "chanel", "coco@gmail.com", "coco",  new BCryptPasswordEncoder().encode("password"), 1,  new Profession(3, "coder"), "en"));

        u.add(new User(4, "anna", "annikova", "anna@gmail.com", "admin", new BCryptPasswordEncoder().encode("password"), 0,  null, "en"));

    }

    public User findByUsername(String username) {
        for (User u : u) {
            if (u.getLogin().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public  User insertUser(User user){
        user.setId(29);
        u.add(user);
        System.out.println(Arrays.toString(u.toArray()));
return user;
    }


    public List<User> getAllUsers() {
        return u.stream().filter
                (a -> a.getRole().getName().equals("USER")).collect(Collectors.toList());
    }
}*/

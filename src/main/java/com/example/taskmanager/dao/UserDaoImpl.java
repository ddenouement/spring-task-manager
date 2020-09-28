package com.example.taskmanager.dao;

import com.example.taskmanager.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Component
public class UserDaoImpl implements IUserDao {
    static ArrayList<User> u = new ArrayList<>();

    static {
        u.add(new User(1, "julia", "aleksandrova", "julia@gmail.com", "user",  new BCryptPasswordEncoder().encode("password"), 1, 1, "en"));
        u.add(new User(1, "whitney", "houston", "whi@gmail.com", "whi",  new BCryptPasswordEncoder().encode("password"), 1, 1, "en"));
        u.add(new User(1, "coco", "chanel", "coco@gmail.com", "coco",  new BCryptPasswordEncoder().encode("password"), 1, 1, "en"));

        u.add(new User(1, "anna", "annikova", "anna@gmail.com", "admin", new BCryptPasswordEncoder().encode("password"), 0, 1, "en"));

    }

    public User findByUsername(String username) {

        for (User u : u) {

            if (u.getLogin().equals(username)) {
                return u;
            }
        }
        return null;
    }
}

package com.example.taskmanager.controller;

import com.example.taskmanager.dao.ITaskRepository;
import com.example.taskmanager.model.Status;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.ITaskService;
import com.example.taskmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    ITaskService taskService;


    @RequestMapping(value = { "/me"}, method = RequestMethod.GET)
    public ModelAndView userProfilePage(  @AuthenticationPrincipal UserDetails currentUser ) {

        User user =  userService.findUserByLogin(currentUser.getUsername());
        ModelAndView model = new ModelAndView();
        model.addObject("currentUser", user);

        model.setViewName("user/profile.html");
        return model;
    }


    @RequestMapping( method = RequestMethod.GET)
    public ModelAndView getAllUsers(@AuthenticationPrincipal UserDetails currentUser) {
        ModelAndView model = new ModelAndView();
        model.addObject("users", userService.findAllUsers());
        model.setViewName("/admin/user/list.html");
        return model;
    }



}

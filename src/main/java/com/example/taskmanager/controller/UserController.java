package com.example.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = { "/dashboard"}, method = RequestMethod.GET)
    public ModelAndView userDashboardPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("userDashboardPage.html");
        return model;
    }

}

package com.example.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = { "/dashboard"}, method = RequestMethod.GET)
    public ModelAndView adminDashboardPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminDashboardPage.html");
        return model;
    }
}

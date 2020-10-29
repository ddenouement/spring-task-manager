package com.example.taskmanager.controller;

import com.example.taskmanager.service.IActivityService;
import com.example.taskmanager.service.ITaskService;
import com.example.taskmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IUserService userService;

    @Autowired
    IActivityService activityService;

    @Autowired
    ITaskService taskService;

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView adminDashboardPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("numActivities", activityService.count());
        model.addObject("numUsers", userService.count());
        model.addObject("numRequestsWaiting", taskService.countWaitingRequests());
        model.addObject("mapOfAverageTimes", activityService.getAverageHoursMap());
        model.setViewName("/admin/adminDashboardPage.html");
        return model;
    }
}

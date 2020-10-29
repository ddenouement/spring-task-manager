package com.example.taskmanager.controller;

import com.example.taskmanager.model.Status;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.ITaskService;
import com.example.taskmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    ITaskService taskService;

    @Autowired
    IUserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {"/requests"}, method = RequestMethod.GET)
    public ModelAndView adminRequestsPage(@RequestParam Optional<String> message) {
        ModelAndView model = new ModelAndView();
        model.addObject("requested", taskService.getAllByStatus(Status.REQUESTED_ADD));
        model.addObject("requested_cancel", taskService.getAllByStatus(Status.REQUESTED_REMOVE));
        model.addObject("assigned", taskService.getAllByStatus(Status.ASSIGNED));
        model.addObject("rejected", taskService.getAllByStatus(Status.REJECTED));
        message.ifPresent(a -> model.addObject("message", a));
        model.setViewName("admin/request/list.html");
        return model;
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = {"/requests/me"}, method = RequestMethod.GET)
    public ModelAndView myRequestsPage(@AuthenticationPrincipal UserDetails currentUser, @RequestParam Optional<String> message) {
        ModelAndView model = new ModelAndView();
        User user =  userService.findUserByLogin(currentUser.getUsername());
        model.addObject("requested", taskService.getAllByStatusByUser(user.getId(), Status.REQUESTED_ADD));
        model.addObject("requested_cancel", taskService.getAllByStatusByUser(user.getId(), Status.REQUESTED_REMOVE));
        message.ifPresent(a -> model.addObject("message", a));
        model.setViewName("user/request/list.html");
        return model;
    }



    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = {"/tasks/me"}, method = RequestMethod.GET)
    public ModelAndView myTasksPage(@AuthenticationPrincipal UserDetails currentUser, @RequestParam Optional<String> message) {
        ModelAndView model = new ModelAndView();
        User user =  userService.findUserByLogin(currentUser.getUsername());
        model.addObject("assigned", taskService.getAllByStatusByUser(user.getId(), Status.ASSIGNED));
        model.addObject("finished", taskService.getAllByStatusByUser(user.getId(), Status.FINISHED));
        message.ifPresent(a -> model.addObject("message", a));
        model.setViewName("user/task/list.html");
        return model;
    }


    @RequestMapping(value = {"/finish"}, method = RequestMethod.POST)
    public ModelAndView doFinishTask(@RequestBody Map<String, Object> userMap, @AuthenticationPrincipal UserDetails currentUser) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle myBundle = ResourceBundle.getBundle("lang", locale);
        String errorMessage = null;
        if (taskService.finishTask((int)userMap.get("id"), (int) userMap.get("hrs"))) {
            errorMessage = myBundle.getString("error");
        }
        return myTasksPage(currentUser, Optional.ofNullable(errorMessage));
    }

    @RequestMapping(value = {"/enroll"}, method = RequestMethod.POST)
    public ModelAndView doEnrollTask(@RequestBody Map<String, Object> userMap, @AuthenticationPrincipal UserDetails currentUser) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle myBundle = ResourceBundle.getBundle("lang", locale);
        String errorMessage = null;
        User user = userService.findUserByLogin(currentUser.getUsername());
        if (taskService.enrollTask((int)userMap.get("id"), user.getId())) {
            errorMessage = myBundle.getString("error");
        }
        return myRequestsPage(currentUser, Optional.ofNullable(errorMessage));
    }


    @RequestMapping(value = {"/cancel"}, method = RequestMethod.POST)
    public ModelAndView doCancelTask(@RequestBody Map<String, Object> userMap, @AuthenticationPrincipal UserDetails currentUser) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle myBundle = ResourceBundle.getBundle("lang", locale);
        String errorMessage = null;
        if (taskService.cancelTask((int)userMap.get("id"))) {
            errorMessage = myBundle.getString("error");
        }
        return myRequestsPage(currentUser, Optional.ofNullable(errorMessage));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {"/do_cancel"}, method = RequestMethod.POST)
    public ModelAndView doAcceptCancelTask(@RequestBody Map<String, Object> userMap, @AuthenticationPrincipal UserDetails currentUser) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle myBundle = ResourceBundle.getBundle("lang", locale);
        String errorMessage = null;
        if (taskService.acceptToCancelTask((int)userMap.get("id"))) {
            errorMessage = myBundle.getString("error");
        }
        return adminRequestsPage( Optional.ofNullable(errorMessage));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {"/reject"}, method = RequestMethod.POST)
    public ModelAndView doRejectTask(@RequestBody Map<String, Object> userMap, @AuthenticationPrincipal UserDetails currentUser) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle myBundle = ResourceBundle.getBundle("lang", locale);
        String errorMessage = null;
        if (taskService.rejectTask((int)userMap.get("id"))) {
            errorMessage = myBundle.getString("error");
        }
        return adminRequestsPage(Optional.ofNullable(errorMessage));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = {"/accept"}, method = RequestMethod.POST)
    public ModelAndView doAcceptTask(@RequestBody Map<String, Object> userMap, @AuthenticationPrincipal UserDetails currentUser) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle myBundle = ResourceBundle.getBundle("lang", locale);
        String errorMessage = null;
        if (taskService.acceptTask((int)userMap.get("id"))) {
            errorMessage = myBundle.getString("error");
        }
        return adminRequestsPage(Optional.ofNullable(errorMessage));
    }
}

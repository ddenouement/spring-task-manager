package com.example.taskmanager.controller;

import com.example.taskmanager.model.User;
import com.example.taskmanager.service.IAuthService;
import com.example.taskmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthController {
    @Autowired
    IAuthService service;


    @RequestMapping(value = { "/register"}, method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("registerPage.html");
        return model;
    }

    @RequestMapping(value = { "/register"}, method = RequestMethod.POST)
    public ModelAndView processRegistration(@Valid User user, Errors errors) {
        ModelAndView model = new ModelAndView();
        if (errors.hasErrors()) {
            model.addObject("error", errors.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(java.util.stream.Collectors.joining(", ")));
            model.setViewName("registerPage.html");
            return model;
        }
        service.register(user);
        model.addObject("message", "registered successful");
        model.setViewName("loginPage.html");
        return model;
    }


    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("home.html");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error                             ) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided");
        }

        model.setViewName("loginPage.html");
        return model;
    }
}

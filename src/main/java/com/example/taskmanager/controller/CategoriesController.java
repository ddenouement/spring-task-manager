package com.example.taskmanager.controller;

import com.example.taskmanager.dto.ActivityDTO;
import com.example.taskmanager.model.Category;
import com.example.taskmanager.service.ICategoryService;
import com.example.taskmanager.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    ICategoryService categoryService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView categories( ) {
        ModelAndView model = new ModelAndView();

        model.addObject("categories", categoryService.getAll());
        model.addObject("newCategory", new Category());
        model.setViewName("/admin/category/list.html");
        return model;
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addCategory(Category newCategory) {

        categoryService.save(newCategory);
        return categories();
    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.POST,  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView deleteCategory(Category activity) {
        categoryService.delete(activity);
        return categories();
    }

}

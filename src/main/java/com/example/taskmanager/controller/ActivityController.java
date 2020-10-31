package com.example.taskmanager.controller;

import com.example.taskmanager.dao.IActivityRepository;
import com.example.taskmanager.dto.ActivityDTO;
import com.example.taskmanager.dto.FilterDTO;
import com.example.taskmanager.model.Activity;
import com.example.taskmanager.model.Status;
import com.example.taskmanager.model.UserActivity;
import com.example.taskmanager.service.IActivityService;
import com.example.taskmanager.service.ICategoryService;
import com.example.taskmanager.service.ITaskService;
import com.example.taskmanager.util.SortParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/activities")
public class ActivityController {
    public class ResourceNotFoundException extends RuntimeException {
    }

    int recordsPerPage = 2;
    @Autowired
    ITaskService taskService;

    @Autowired
    ICategoryService categoryService;
    @Autowired
    IActivityService activityService;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "notfound.html";
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllActivities(
            @AuthenticationPrincipal UserDetails currentUser,
            Optional<FilterDTO> filterSent) {

        ModelAndView model = new ModelAndView();
        FilterDTO filter = filterSent.orElse(new FilterDTO());
        long numOfRequests = 0;

        if (currentUser != null && currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            model.addObject("activities", activityService.getAllSorted(filter, filter.getPage(), recordsPerPage));
            numOfRequests = activityService.countAllFiltered(filter);
        } else {
            System.out.println("enabled activities page" + filter.getPage());
            for (Activity a : activityService.getAllEnabledSorted(filter, filter.getPage(), recordsPerPage))
                System.out.print(a.getEnabled() + " ");
            System.out.println();

            model.addObject("activities", activityService.getAllEnabledSorted(filter, filter.getPage(), recordsPerPage));
            numOfRequests = activityService.countActiveFiltered(filter);
        }

        int noOfPages = (int) Math.ceil(numOfRequests * 1.0 / recordsPerPage);
        model.addObject("categories", categoryService.getAll());
        model.addObject("currentPage", filter.getPage());
        model.addObject("noOfPages", noOfPages);
        model.addObject("filter", filter);
        model.setViewName("listActivities.html");
        return model;
    }


    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public ModelAndView getActivity(@AuthenticationPrincipal UserDetails currentUser,
                                    @PathVariable int id,
                                    @RequestParam(name = "progress") Optional<String> progress) {
        ModelAndView model = new ModelAndView();
        Activity found = activityService.getById(id).orElseThrow(() -> new ResourceNotFoundException());
        model.addObject("activity", found);
        Status progressParam;
        try {
            progressParam = Status.valueOf(progress.orElse("ASSIGNED").toUpperCase());
        } catch (IllegalArgumentException ignored) {
            progressParam = Status.ASSIGNED;
        }
        List<UserActivity> tasks = taskService.getAllByStatusByActivity(progressParam, id);
        model.setViewName("viewActivity.html");
        model.addObject("tasks", tasks);
        model.addObject("filterByProgress", progressParam.getName().toUpperCase());
        model.addObject("progresses", new Status[]{Status.REQUESTED_ADD, Status.ASSIGNED, Status.FINISHED});
        return model;
    }


    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public ModelAndView newActivity(@AuthenticationPrincipal UserDetails currentUser) {
        ModelAndView model = new ModelAndView();

        model.addObject("allCategories", categoryService.getAll());
        model.addObject("activity", new ActivityDTO());
        model.setViewName("/admin/activity/add.html");
        return model;
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView addActivity(@AuthenticationPrincipal UserDetails currentUser,
                                    ActivityDTO newactivity) {

        System.out.println(newactivity.getCategoryIds().toString());

        activityService.saveDTO(newactivity);
        //     return   new ModelAndView( "redirect:/activities");
        return getAllActivities(currentUser, Optional.empty());
    }


    @RequestMapping(value = {"/disable"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView disableActivity(Activity activity,
                                        @AuthenticationPrincipal UserDetails current) {
        Optional<Activity> found = activityService.getById(activity.getId());
        found.orElseThrow(() -> new ResourceNotFoundException());
        found.get().setEnabled(false);
        found.ifPresent(activityService::save);
        //    return   new ModelAndView( "redirect:/activities/"+activity.getId());
        return getAllActivities(current, Optional.empty());
    }

    @RequestMapping(value = {"/enable"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView enableActivity(Activity activity, @AuthenticationPrincipal UserDetails current) {
        Optional<Activity> found = activityService.getById(activity.getId());
        found.orElseThrow(() -> new ResourceNotFoundException());
        found.get().setEnabled(true);
        found.ifPresent(activityService::save);
        //   return   new ModelAndView( "redirect:/activities/"+activity.getId());
        return getAllActivities(current, Optional.empty());
    }

    @RequestMapping(value = {"/{id}/edit"}, method = RequestMethod.GET)
    public ModelAndView editActivityPage(@PathVariable("id") int id) {
        ModelAndView model = new ModelAndView();
        Activity found = activityService.getById(id).orElseThrow(() -> new ResourceNotFoundException());
        model.addObject("activity", found.getDTO());
        model.addObject("allCategories", categoryService.getAll());
        model.setViewName("/admin/activity/edit.html");
        return model;
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView editActivity(@AuthenticationPrincipal UserDetails currentUser,
                                     ActivityDTO editedActivity) {
         activityService.saveDTO(editedActivity);
        return new ModelAndView("redirect:/activities");
    }
}

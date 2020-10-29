package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.IActivityRepository;
import com.example.taskmanager.dao.ITaskRepository;
import com.example.taskmanager.dao.IUserRepository;
import com.example.taskmanager.model.Activity;
import com.example.taskmanager.model.Status;
import com.example.taskmanager.model.User;
import com.example.taskmanager.model.UserActivity;
import com.example.taskmanager.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    ITaskRepository taskRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IActivityRepository activityRepository;

    @Override
    public Long count() {
        return taskRepository.count();
    }

    @Override
    public List<UserActivity> getAllByStatusByUser(int user_id, Status st) {
        return (List<UserActivity>) taskRepository.findByStatusByIdUserAndOrderByCreatedon(st, user_id);
    }

    @Override
    public List<UserActivity> getAllByStatus(Status st) {
        return (List<UserActivity>) taskRepository.findByStatusAndOrderByCreatedon(st);
    }

    @Override
    public List<UserActivity> getAll() {
        return (List<UserActivity>) taskRepository.findAll();
    }

    @Override
    public Optional<UserActivity> getById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public UserActivity save(UserActivity activity) {
        return taskRepository.save(activity);
    }

    @Transactional
    @Override
    public boolean finishTask(int id, int hrs) {
        Optional<UserActivity> activityOpt = taskRepository.findById(id);
        if (!activityOpt.isPresent()) return false;
        UserActivity activity = activityOpt.get();
        if (activity.getStatus() == Status.ASSIGNED) {
            activity.setStatus(Status.FINISHED);
            activity.setFinishedOn(ZonedDateTime.now());
            activity.setTimeSpentInHours(hrs);
            taskRepository.save(activity);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean acceptTask(int id) {
        Optional<UserActivity> activityOpt = taskRepository.findById(id);
        if (!activityOpt.isPresent()) return false;
        UserActivity activity = activityOpt.get();
        if (activity.getStatus() == Status.REQUESTED_ADD) {
            activity.setStatus(Status.ASSIGNED);
            taskRepository.save(activity);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean rejectTask(int id) {
        Optional<UserActivity> activityOpt = taskRepository.findById(id);
        if (!activityOpt.isPresent()) return false;
        UserActivity activity = activityOpt.get();
        if (activity.getStatus() == Status.REQUESTED_ADD) {
            activity.setStatus(Status.REJECTED);
            taskRepository.save(activity);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean cancelTask(int id) {
        Optional<UserActivity> activityOpt = taskRepository.findById(id);
        if (!activityOpt.isPresent()) return false;
        UserActivity activity = activityOpt.get();
        if (activity.getStatus() == Status.ASSIGNED  ) {
            activity.setStatus(Status.REQUESTED_REMOVE);
            taskRepository.save(activity);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean enrollTask(int id, int user_id) {
        if (taskRepository.findUnfinishedByIdUserByIdActivity(user_id, id).isEmpty()) {
            Activity activity;
            Optional<Activity> optionalActivity = activityRepository.findById(id);
            if (!optionalActivity.isPresent()) return false;
            activity = optionalActivity.get();
            User user;
            Optional<User> userOptional = userRepository.findById(id);
            if (!userOptional.isPresent()) return false;
            user = userOptional.get();
            UserActivity userActivity = new UserActivity();
            userActivity.setUser(user);
            userActivity.setActivity(activity);
            userActivity.setStatus(Status.REQUESTED_ADD);
            userActivity.setCreatedOn(ZonedDateTime.now());
            taskRepository.save(userActivity);
            return true;
        } else return false;
    }

    @Transactional
    @Override
    public boolean acceptToCancelTask(int id) {
        Optional<UserActivity> activityOpt = taskRepository.findById(id);
        if (!activityOpt.isPresent()) return false;
        UserActivity activity = activityOpt.get();
        if (activity.getStatus() == Status.REQUESTED_REMOVE  ) {
            activity.setStatus(Status.CANCELLED);
            taskRepository.save(activity);
            return true;
        }
        return false;
    }

    @Override
    public List<UserActivity> getAllByStatusByActivity(Status st, int id) {
        return taskRepository.getAllByStatusByActivity(st,id);
    }

    @Override
    public Long countWaitingRequests() {
        Set <Status> set = new HashSet<>();
        set.add(Status.REQUESTED_ADD);
        set.add(Status.REQUESTED_REMOVE);
        return taskRepository.countByStatusIn(set);
    }

}

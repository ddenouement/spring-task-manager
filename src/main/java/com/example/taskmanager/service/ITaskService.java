package com.example.taskmanager.service;

import com.example.taskmanager.model.Activity;
import com.example.taskmanager.model.Status;
import com.example.taskmanager.model.UserActivity;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    Long count();

    List<UserActivity> getAll();

    Optional<UserActivity> getById(int id);

    UserActivity save(UserActivity activity);

      List<UserActivity> getAllByStatusByUser(int user_id, Status st);

      List<UserActivity> getAllByStatus(Status st);


    boolean acceptTask(int id);
    boolean enrollTask(int id, int user_id);
    boolean rejectTask(int id);
    boolean cancelTask(int id);
    boolean finishTask(int id, int hrs);
    boolean acceptToCancelTask(int id);
    List<UserActivity> getAllByStatusByActivity(Status st, int id);

    Long countWaitingRequests();
}

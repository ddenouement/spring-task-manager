package com.example.taskmanager.dao;

import com.example.taskmanager.model.Status;
import com.example.taskmanager.model.UserActivity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.DoubleStream;

@Repository
public interface ITaskRepository extends CrudRepository<UserActivity, Integer> {
    @Query("FROM UserActivity WHERE (status = ?1 and id_user=?2) ORDER BY created_request_at DESC")
    List<UserActivity> findByStatusByIdUserAndOrderByCreatedon(Status status, int id_user);

    @Query("FROM UserActivity WHERE status = ?1 ORDER BY created_request_at DESC")
    List<UserActivity> findByStatusAndOrderByCreatedon(Status status);

    @Query("FROM UserActivity WHERE ((status <> 'FINISHED' and status <> 'CANCELLED' and status <> 'REJECTED') and id_user=?2 and id_activity=?3)")
    List<UserActivity> findUnfinishedByIdUserByIdActivity(  int id_user, int id_activity);

    @Query("FROM UserActivity WHERE (status = ?1 and id_activity=?2)")
    List <UserActivity> getAllByStatusByActivity(Status status, int id);

    @Query("select count(e) from UserActivity e where e.status in :ids")
    Long countByStatusIn(@Param("ids") Set<Status> statusSet);

}

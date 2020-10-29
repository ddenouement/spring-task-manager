package com.example.taskmanager.dao;

import com.example.taskmanager.model.Activity;
import com.example.taskmanager.model.UserActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IActivityRepository extends PagingAndSortingRepository<Activity, Integer> {

    Page<Activity> findByEnabledTrueAndCategories_IdIn(List<Integer> ids, Pageable b);

    Page<Activity> findByCategories_IdIn(List<Integer> ids, Pageable b);

    Page<Activity> findByEnabledTrue(Pageable b);

    long countByEnabledTrueAndCategories_IdIn(List<Integer> categoryIds);

    long countByEnabledTrue();

    long countByCategories_IdIn(List<Integer> categoryIds);
}

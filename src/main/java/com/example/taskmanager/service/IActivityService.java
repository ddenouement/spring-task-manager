package com.example.taskmanager.service;

import com.example.taskmanager.dto.ActivityDTO;
import com.example.taskmanager.dto.FilterDTO;
import com.example.taskmanager.model.Activity;
import com.example.taskmanager.util.SortParam;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface IActivityService {
    public void delete(Activity a);

    Long count();

    List<Activity> getAll();

    Set<Activity> getAllEnabledSorted(FilterDTO filterDTO, int page, int offset);
    Set<Activity> getAllSorted(FilterDTO filterDTO, int page, int offset);

    Optional<Activity> getById(int id);

    Activity save(Activity activity);

    Activity saveDTO(ActivityDTO activity);

    Map<String, Double> getAverageHoursMap();

    long countAllFiltered(FilterDTO filter);

    long countActiveFiltered(FilterDTO filter);
}

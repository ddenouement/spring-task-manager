package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.IActivityRepository;
import com.example.taskmanager.dao.ICategoryRepository;
import com.example.taskmanager.dao.ITaskRepository;
import com.example.taskmanager.dto.ActivityDTO;
import com.example.taskmanager.dto.FilterDTO;
import com.example.taskmanager.model.Activity;
import com.example.taskmanager.model.Category;
import com.example.taskmanager.model.UserActivity;
import com.example.taskmanager.service.IActivityService;
import com.example.taskmanager.service.ITaskService;
import com.example.taskmanager.util.SortParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ActivityServiceImpl implements IActivityService {
    @Autowired
    IActivityRepository activityRepository;
    @Autowired
    ITaskRepository taskRepository;
    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public Long count() {
        return activityRepository.count();
    }


    @Override
    public void delete(Activity a) {
        activityRepository.deleteById(a.getId());
    }


    @Override
    public List<Activity> getAll() {
        return (List<Activity>) activityRepository.findAll();
    }


    @Override
    public Set<Activity> getAllSorted(FilterDTO filterDTO, int page, int size) {
        Page<Activity> result;
        Pageable pageable = PageRequest.of(page, size, Sort.by(filterDTO.getSortBy().name().toLowerCase()));
        if (filterDTO.getCategoryIds() == null || filterDTO.getCategoryIds().isEmpty()) {
            result = activityRepository.findAll(pageable);
            return result.toSet();
        } else {
            result = activityRepository.findByCategories_IdIn(filterDTO.getCategoryIds(), pageable);
            return result.toSet();
        }
    }

    @Override
    public Set<Activity> getAllEnabledSorted(FilterDTO filterDTO, int page, int size) {
        Page<Activity> result;
        Pageable pageable = PageRequest.of(page, size, Sort.by(filterDTO.getSortBy().name().toLowerCase()));
        if (filterDTO.getCategoryIds() == null || filterDTO.getCategoryIds().isEmpty()) {
            result = activityRepository.findByEnabledTrue(pageable);

            System.out.print("HERE");
            return result.toSet();
        } else {
            result = activityRepository.findByEnabledTrueAndCategories_IdIn(filterDTO.getCategoryIds(), pageable);
            return result.toSet();
        }
    }


    @Override
    public Optional<Activity> getById(int id) {
        return activityRepository.findById(id);
    }

    @Override
    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity saveDTO(ActivityDTO dto) {
        List<Category> c;
        if (dto.getCategoryIds() == null || dto.getCategoryIds().isEmpty()) {
            c = new ArrayList();
        }
        c = ((ArrayList) categoryRepository.findAllById(dto.getCategoryIds()));

        Activity activity1 = Activity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .nameEn(dto.getNameEn())
                .nameUa(dto.getNameUa())
                .description(dto.getDescription())
                .descriptionEn(dto.getDescriptionEn())
                .descriptionUa(dto.getDescriptionUa())
                .categories(c).build();

        activityRepository.save(activity1);
        return activity1;
    }

    @Override
    public Map<String, Double> getAverageHoursMap() {
        List<UserActivity> found = (ArrayList<UserActivity>) (taskRepository.findAll());
        Map<String, Double> result = found.stream()
                .collect(Collectors.groupingBy(a -> a.getActivity().getName(),
                        Collectors.averagingInt(UserActivity::getTimeSpentInHours)));
        return result;
    }

    @Override
    public long countAllFiltered(FilterDTO filterDTO) {
        long result;
        if (filterDTO.getCategoryIds() == null || filterDTO.getCategoryIds().isEmpty()) {
            result = activityRepository.count();
        } else {
            result = activityRepository.countByCategories_IdIn(filterDTO.getCategoryIds());
        }
        return result;
    }

    @Override
    public long countActiveFiltered(FilterDTO filterDTO) {
        long result;
        if (filterDTO.getCategoryIds() == null || filterDTO.getCategoryIds().isEmpty()) {
            result = activityRepository.countByEnabledTrue();
        } else {
            result = activityRepository.countByEnabledTrueAndCategories_IdIn(filterDTO.getCategoryIds());
        }
        return result;
    }

}

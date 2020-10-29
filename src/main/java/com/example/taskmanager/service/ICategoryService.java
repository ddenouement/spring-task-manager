package com.example.taskmanager.service;

import com.example.taskmanager.model.Category;
import org.springframework.scheduling.config.Task;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Long count();
    List<Category> getAll();
    Optional<Category> getById(int id);
    Category  save(Category activity);
      void delete(Category category);
}

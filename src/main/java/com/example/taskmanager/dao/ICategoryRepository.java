package com.example.taskmanager.dao;

import com.example.taskmanager.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category, Integer> {
}

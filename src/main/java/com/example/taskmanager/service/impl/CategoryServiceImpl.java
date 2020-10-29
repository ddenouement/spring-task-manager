package com.example.taskmanager.service.impl;

import com.example.taskmanager.dao.ICategoryRepository;
import com.example.taskmanager.model.Category;
import com.example.taskmanager.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public Long count() {
        return categoryRepository.count();
    }

    @Override
    public List<Category> getAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(Category category) {  categoryRepository.deleteById(category.getId()); }

    @Override
    public Category save(Category activity) {
        return categoryRepository.save(activity);
    }
}

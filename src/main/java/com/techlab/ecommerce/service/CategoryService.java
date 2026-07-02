package com.techlab.ecommerce.service;

import com.techlab.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    Category update(Long id, Category category);

    void deleteById(Long id);

}

package com.techlab.ecommerce.service.impl;

import com.techlab.ecommerce.entity.Category;
import com.techlab.ecommerce.exception.ResourceNotFoundException;
import com.techlab.ecommerce.repository.CategoryRepository;
import com.techlab.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {

        Category existingCategory = findById(id);

        if (existingCategory == null) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }

        existingCategory.setName(category.getName());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}

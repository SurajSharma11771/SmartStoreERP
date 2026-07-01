package com.smartstore.erp.category.service;

import com.smartstore.erp.category.dto.CategoryResponse;
import com.smartstore.erp.category.dto.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CreateCategoryRequest request);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long id);

    CategoryResponse updateCategory(Long id, CreateCategoryRequest request);

    void deleteCategory(Long id);
}
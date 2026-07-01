package com.smartstore.erp.category.service;

import com.smartstore.erp.category.dto.CategoryResponse;
import com.smartstore.erp.category.dto.CreateCategoryRequest;
import com.smartstore.erp.category.entity.Category;
import com.smartstore.erp.category.repository.CategoryRepository;
import com.smartstore.erp.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request) {

        if (categoryRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Category name already exists");
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .status(true)
                .createdAt(LocalDateTime.now())
                .build();

        Category savedCategory = categoryRepository.save(category);

        return CategoryResponse.builder()
                .id(savedCategory.getId())
                .name(savedCategory.getName())
                .description(savedCategory.getDescription())
                .status(savedCategory.getStatus())
         
               .build();
    }
private CategoryResponse mapToResponse(Category category) {
    return CategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .description(category.getDescription())
            .status(category.getStatus())
            .build();
}

@Override
public java.util.List<CategoryResponse> getAllCategories() {
    return categoryRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .toList();
}

@Override
public CategoryResponse getCategoryById(Long id) {
    Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new com.smartstore.erp.exception.ResourceNotFoundException("Category not found"));

    return mapToResponse(category);
}

@Override
public CategoryResponse updateCategory(Long id, CreateCategoryRequest request) {
    Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new com.smartstore.erp.exception.ResourceNotFoundException("Category not found"));

    category.setName(request.getName());
    category.setDescription(request.getDescription());
    category.setUpdatedAt(java.time.LocalDateTime.now());

    Category updatedCategory = categoryRepository.save(category);

    return mapToResponse(updatedCategory);
}

@Override
public void deleteCategory(Long id) {
    Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new com.smartstore.erp.exception.ResourceNotFoundException("Category not found"));

    categoryRepository.delete(category);
}

}
package com.smartstore.erp.category.controller;

import com.smartstore.erp.category.dto.CategoryResponse;
import com.smartstore.erp.category.dto.CreateCategoryRequest;
import com.smartstore.erp.category.service.CategoryService;
import com.smartstore.erp.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        return ApiResponse.success(
                "Category created successfully",
                categoryService.createCategory(request)
        );
    }
    @GetMapping
public ApiResponse<java.util.List<CategoryResponse>> getAllCategories() {
    return ApiResponse.success(
            "Categories fetched successfully",
            categoryService.getAllCategories()
    );
}

@GetMapping("/{id}")
public ApiResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
    return ApiResponse.success(
            "Category fetched successfully",
            categoryService.getCategoryById(id)
    );
}

@PutMapping("/{id}")
public ApiResponse<CategoryResponse> updateCategory(
        @PathVariable Long id,
        @Valid @RequestBody CreateCategoryRequest request) {

    return ApiResponse.success(
            "Category updated successfully",
            categoryService.updateCategory(id, request)
    );
}

@DeleteMapping("/{id}")
public ApiResponse<Object> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ApiResponse.success("Category deleted successfully", null);
}
}
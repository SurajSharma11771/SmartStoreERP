package com.smartstore.erp.product.controller;

import com.smartstore.erp.common.ApiResponse;
import com.smartstore.erp.product.dto.CreateProductRequest;
import com.smartstore.erp.product.dto.ProductResponse;
import com.smartstore.erp.product.dto.UpdateProductRequest;
import com.smartstore.erp.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductResponse response = productService.createProduct(request);
        return ApiResponse.success("Product created successfully", response);
    }
    @GetMapping
public ApiResponse<List<ProductResponse>> getAllProducts() {
    List<ProductResponse> products = productService.getAllProducts();
    return ApiResponse.success("Products fetched successfully", products);
}
@GetMapping("/{id}")
public ApiResponse<ProductResponse> getProductById(@PathVariable Long id) {
    ProductResponse product = productService.getProductById(id);
    return ApiResponse.success("Product fetched successfully", product);
}
@PutMapping("/{id}")
public ApiResponse<ProductResponse> updateProduct(
        @PathVariable Long id,
        @Valid @RequestBody UpdateProductRequest request) {

    ProductResponse product = productService.updateProduct(id, request);
    return ApiResponse.success("Product updated successfully", product);
}
@DeleteMapping("/{id}")
public ApiResponse<Object> deleteProduct(@PathVariable Long id) {

    productService.deleteProduct(id);

    return ApiResponse.success("Product deleted successfully", null);
}
@GetMapping("/search")
public ApiResponse<List<ProductResponse>> searchProducts(@RequestParam String keyword) {
    return ApiResponse.success(
            "Products searched successfully",
            productService.searchProducts(keyword)
    );
}

@GetMapping("/low-stock")
public ApiResponse<List<ProductResponse>> getLowStockProducts() {
    return ApiResponse.success(
            "Low stock products fetched successfully",
            productService.getLowStockProducts()
    );
}

}
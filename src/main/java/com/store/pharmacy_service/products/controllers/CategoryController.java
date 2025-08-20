package com.store.pharmacy_service.products.controllers;

import com.store.pharmacy_service.products.application.CategoryService;
import com.store.pharmacy_service.products.domain.DTOs.CategoryRequest;
import com.store.pharmacy_service.products.domain.DTOs.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired private CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.findCategoryById(categoryId));
    }

    @PostMapping
    public CategoryResponse create(@RequestBody CategoryRequest category) {
        return categoryService.saveCategory(category);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryRequest category) {
        return categoryService.findById(id)
                .map(existing -> {
                    existing.setName(category.getName());
                    existing.setDescription(category.getDescription());
                    return ResponseEntity.ok(categoryService.save(existing));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return categoryService.findById(id).map(cat -> {
            categoryService.delete(cat);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }*/
}
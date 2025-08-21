package com.store.pharmacy_service.products.utils.mappers;

import com.store.pharmacy_service.products.domain.DTOs.CategoryRequest;
import com.store.pharmacy_service.products.domain.DTOs.CategoryResponse;
import com.store.pharmacy_service.products.domain.entities.Category;

public class MapCategory {

    public static CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .description(category.getDescription())
                .categoryId(category.getId())
                .name(category.getName()).build();
    }

    public static Category mapToCategory(CategoryRequest category) {
        return Category.builder()
                .description(category.getDescription())
                .name(category.getName())
                .id(category.getCategoryId()).build();
    }
}

package com.store.pharmacy_service.products.application;

import com.store.pharmacy_service.products.domain.DTOs.CategoryRequest;
import com.store.pharmacy_service.products.domain.DTOs.CategoryResponse;
import com.store.pharmacy_service.products.domain.entities.Category;
import com.store.pharmacy_service.products.domain.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    @Autowired private CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = Streamable.of(categoryRepository.findAll()).toList();
        return categories.stream().map(this::mapToCategoryResponse).toList();
    }

    public CategoryResponse findCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        assert category != null;
        return mapToCategoryResponse(category);
    }

    public CategoryResponse saveCategory(CategoryRequest category) {
        Category categoryToSave = Category.builder()
                .name(category.getName())
                .description(category.getDescription()).build();

        Category savedCategory = categoryRepository.save(categoryToSave);
        return Objects.nonNull(savedCategory.getId()) ? this.mapToCategoryResponse(savedCategory) : null;
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .description(category.getDescription()).build();
    }

}

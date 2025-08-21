package com.store.pharmacy_service.products.application;

import com.store.pharmacy_service.products.domain.DTOs.CategoryRequest;
import com.store.pharmacy_service.products.domain.DTOs.CategoryResponse;
import com.store.pharmacy_service.products.domain.entities.Category;
import com.store.pharmacy_service.products.domain.repositories.CategoryRepository;
import com.store.pharmacy_service.products.utils.mappers.MapCategory;
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
        return categories.stream().map(MapCategory::mapToCategoryResponse).toList();
    }

    public CategoryResponse findCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        assert category != null;
        return MapCategory.mapToCategoryResponse(category);
    }

    public CategoryResponse saveCategory(CategoryRequest category) {
        Category categoryToSave = Category.builder()
                .name(category.getName())
                .description(category.getDescription()).build();

        Category savedCategory = categoryRepository.save(categoryToSave);
        return Objects.nonNull(savedCategory.getId()) ? MapCategory.mapToCategoryResponse(savedCategory) : null;
    }

}

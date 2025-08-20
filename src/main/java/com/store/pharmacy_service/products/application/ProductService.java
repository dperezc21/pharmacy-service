package com.store.pharmacy_service.products.application;

import com.store.pharmacy_service.products.domain.DTOs.CategoryRequest;
import com.store.pharmacy_service.products.domain.DTOs.LaboratoryRequest;
import com.store.pharmacy_service.products.domain.DTOs.ProductRequest;
import com.store.pharmacy_service.products.domain.DTOs.ProductResponse;
import com.store.pharmacy_service.products.domain.entities.Category;
import com.store.pharmacy_service.products.domain.entities.Laboratory;
import com.store.pharmacy_service.products.domain.entities.Product;
import com.store.pharmacy_service.products.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;

    public ProductResponse saveProduct(ProductRequest productRequest) {
        Product productToSave = Product.builder()
                .sku(productRequest.getCode())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .laboratory(this.mapToLaboratory(productRequest.getLaboratory()))
                .category(this.mapToCategory(productRequest.getCategory())).build();
        Product result = productRepository.save(productToSave);
        return this.mapToProductResponse(result);
    }

    public List<ProductResponse> getAllProducts() {
        return Streamable.of(this.productRepository.findAll())
                .stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId())
                .code(product.getSku())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

    private Laboratory mapToLaboratory(LaboratoryRequest laboratoryRequest) {
        return Laboratory.builder()
                .description(laboratoryRequest.getDescription())
                .name(laboratoryRequest.getName())
                .id(laboratoryRequest.getLaboratoryId()).build();
    }

    private Category mapToCategory(CategoryRequest category) {
        return Category.builder()
                .description(category.getDescription())
                .name(category.getName())
                .id(category.getCategoryId()).build();
    }
}

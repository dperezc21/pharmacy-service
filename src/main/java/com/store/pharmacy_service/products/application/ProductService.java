package com.store.pharmacy_service.products.application;

import com.store.pharmacy_service.products.domain.DTOs.*;
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
                .category(this.mapToCategory(productRequest.getCategory()))
                .iva(productRequest.getIva())
                .productWeight(productRequest.getProductWeight()).build();
        Product result = productRepository.save(productToSave);
        return this.mapToProductResponse(result);
    }

    public ProductResponse editProduct(Long productId, ProductRequest productRequest) {
        Product findProductToEdit = this.productRepository.findById(productId).orElse(null);
        if(findProductToEdit == null) return null;
        Product productToSave = Product.builder()
                .id(findProductToEdit.getId())
                .sku(findProductToEdit.getSku())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .laboratory(this.mapToLaboratory(productRequest.getLaboratory()))
                .category(this.mapToCategory(productRequest.getCategory()))
                .iva(productRequest.getIva())
                .productWeight(productRequest.getProductWeight()).build();
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
                .category(this.mapToCategoryResponse(product.getCategory()))
                .productWeight(product.getProductWeight())
                .iva(product.getIva())
                .laboratory(this.mapToLaboratoryResponse(product.getLaboratory()))
                .build();
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .description(category.getDescription())
                .categoryId(category.getId())
                .name(category.getName()).build();
    }

    private LaboratoryResponse mapToLaboratoryResponse(Laboratory laboratory) {
        return LaboratoryResponse.builder()
                .description(laboratory.getDescription())
                .laboratoryId(laboratory.getId())
                .name(laboratory.getName()).build();
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

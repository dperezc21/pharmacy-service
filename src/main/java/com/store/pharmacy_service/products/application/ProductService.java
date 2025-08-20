package com.store.pharmacy_service.products.application;

import com.store.pharmacy_service.products.domain.DTOs.ProductRequest;
import com.store.pharmacy_service.products.domain.DTOs.ProductResponse;
import com.store.pharmacy_service.products.domain.entities.Product;
import com.store.pharmacy_service.products.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired private ProductRepository productRepository;

    public void saveProduct(ProductRequest productRequest) {
        Product productToSave = Product.builder()
                .sku(productRequest.getCode())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();
        productRepository.save(productToSave);
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
}

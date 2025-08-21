package com.store.pharmacy_service.products.utils.mappers;

import com.store.pharmacy_service.products.domain.DTOs.ProductResponse;
import com.store.pharmacy_service.products.domain.entities.Product;

public class MapProduct {
    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId())
                .code(product.getSku())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .category(MapCategory.mapToCategoryResponse(product.getCategory()))
                .productWeight(product.getProductWeight())
                .iva(product.getIva())
                .laboratory(MapLaboratory.mapToLaboratoryResponse(product.getLaboratory()))
                .build();
    }
}

package com.store.pharmacy_service.products.utils.mappers;

import com.store.pharmacy_service.products.domain.DTOs.ProductResponse;
import com.store.pharmacy_service.products.domain.entities.Product;

public class MapProduct {
    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId())
                .code(product.getSku())
                .packageSalePrice(product.getPackageSalePrice())
                .name(product.getName())
                .description(product.getDescription())
                .category(MapCategory.mapToCategoryResponse(product.getCategory()))
                .presentation(product.getPresentation())
                .laboratory(MapLaboratory.mapToLaboratoryResponse(product.getLaboratory()))
                .salePrice(product.getSalePrice())
                .build();
    }
}

package com.store.pharmacy_service.products.utils.mappers;

import com.store.pharmacy_service.products.application.TypePriceComponent;
import com.store.pharmacy_service.products.domain.DTOs.ProductResponse;
import com.store.pharmacy_service.products.domain.DTOs.PriceTypeResponse;
import com.store.pharmacy_service.products.domain.entities.Product;
import com.store.pharmacy_service.products.domain.entities.PriceType;

import java.util.List;
import java.util.Objects;

public class MapProduct {
    private static final TypePriceComponent TYPE_PRICE_COMPONENT = TypePriceComponent.getProductTypePriceComponent();
    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId())
                .code(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .category(MapCategory.mapToCategoryResponse(product.getCategory()))
                .presentation(product.getPresentation())
                .laboratory(MapLaboratory.mapToLaboratoryResponse(product.getLaboratory()))
                .priceTypes(priceTypesResponse(product))
                .build();
    }

    public static List<PriceTypeResponse> priceTypesResponse(Product product) {
        return TYPE_PRICE_COMPONENT.typePricesResponse().stream().peek(priceTypeResponse -> {
            if(priceTypeResponse.getType().equalsIgnoreCase(PriceType.BOX.name())) {
                priceTypeResponse.setPrice(product.getBoxPrice());
                if(Objects.nonNull(product.getBoxPrice())) priceTypeResponse.setSelected(product.getBoxPrice() != 0);
            } else if(priceTypeResponse.getType().equalsIgnoreCase(PriceType.BLISTER.name())) {
                priceTypeResponse.setPrice(product.getBlisterPrice());
                if(Objects.nonNull(product.getBlisterPrice())) priceTypeResponse.setSelected(product.getBlisterPrice() != 0);
            } else if(priceTypeResponse.getType().equalsIgnoreCase(PriceType.UNIT.name())) {
                priceTypeResponse.setPrice(product.getUnitPrice());
                if(Objects.nonNull(product.getUnitPrice())) priceTypeResponse.setSelected(product.getUnitPrice() != 0);
            } else if(priceTypeResponse.getType().equalsIgnoreCase(PriceType.PACKAGE.name())) {
                priceTypeResponse.setPrice(product.getPackagePrice());
                if(Objects.nonNull(product.getPackagePrice())) priceTypeResponse.setSelected(product.getPackagePrice() != 0);
            }
        }).toList();
    }
}

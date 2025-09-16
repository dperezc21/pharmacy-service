package com.store.pharmacy_service.products.application;

import com.store.pharmacy_service.products.domain.DTOs.PriceTypeResponse;
import com.store.pharmacy_service.products.domain.entities.PriceType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TypePriceComponent {

    private static TypePriceComponent typePriceComponent;

    private TypePriceComponent() {}

    public static TypePriceComponent getProductTypePriceComponent() {
        if(TypePriceComponent.typePriceComponent == null)
            TypePriceComponent.typePriceComponent = new TypePriceComponent();
        return typePriceComponent;
    }

    public List<PriceTypeResponse> typePricesResponse() {
        return Arrays.stream(PriceType.values()).map(priceType -> PriceTypeResponse.builder()
                .price(0D)
                .type(priceType.name().toLowerCase())
                .label(priceType.getLabel())
                .selected(false)
                .build()).toList();
    }
}

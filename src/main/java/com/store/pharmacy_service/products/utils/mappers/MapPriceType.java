package com.store.pharmacy_service.products.utils.mappers;

import com.store.pharmacy_service.products.domain.DTOs.PriceTypeResponse;
import com.store.pharmacy_service.products.domain.DTOs.PriceTypesRequest;
import com.store.pharmacy_service.products.domain.entities.PriceType;

import java.util.List;

public class MapPriceType {

    public static List<PriceTypeResponse> priceTypesResponse(List<PriceType> priceTypes) {
        return priceTypes.stream().map(priceType -> PriceTypeResponse.builder()
                .priceTypeId(priceType.getId())
                .price(priceType.getPrice())
                .type(priceType.getType())
                .label(priceType.getLabel())
                .quantity(priceType.getQuantity())
                .selected(priceType.getPrice() != 0)
                .build()).parallel().toList();
    }

    public static PriceType mapToProductPriceType(PriceTypesRequest priceTypesRequest) {
        PriceType type = PriceType.builder()
                .price(priceTypesRequest.getPrice())
                .type(priceTypesRequest.getType())
                .label(priceTypesRequest.getLabel())
                .quantity(priceTypesRequest.getQuantity())
                .build();
        if(priceTypesRequest.getPriceTypeId() != null) type.setId(priceTypesRequest.getPriceTypeId());
        return type;
    }
}

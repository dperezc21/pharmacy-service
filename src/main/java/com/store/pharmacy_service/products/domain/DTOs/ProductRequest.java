package com.store.pharmacy_service.products.domain.DTOs;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    private String code;
    private String name;
    private String description;
    private CategoryRequest category;
    private LaboratoryRequest laboratory;
    private String presentation;
    private List<PriceTypesRequest> priceTypes;
}

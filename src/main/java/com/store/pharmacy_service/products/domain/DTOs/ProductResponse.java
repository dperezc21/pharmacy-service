package com.store.pharmacy_service.products.domain.DTOs;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private String description;
    private CategoryResponse category;
    private LaboratoryResponse laboratory;
    private String presentation;
    private List<PriceTypeResponse> priceTypes;
}

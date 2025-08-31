package com.store.pharmacy_service.products.domain.DTOs;

import lombok.*;

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
    private Double packageSalePrice;
    private CategoryResponse category;
    private LaboratoryResponse laboratory;
    private Double salePrice;
    private String presentation;
}

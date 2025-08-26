package com.store.pharmacy_service.products.domain.DTOs;

import lombok.*;

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
    private Double price;
    private CategoryRequest category;
    private Double productWeight;
    private Double iva;
    private LaboratoryRequest laboratory;
    private Double salePrice;
}

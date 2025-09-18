package com.store.pharmacy_service.products.domain.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceTypesRequest {
    private Long priceTypeId;
    private String type;
    private String label;
    private Double price;
    private Integer quantity;
}

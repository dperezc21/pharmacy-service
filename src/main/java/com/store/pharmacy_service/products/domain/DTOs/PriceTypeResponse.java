package com.store.pharmacy_service.products.domain.DTOs;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceTypeResponse {
    private Long priceTypeId;
    private String type;
    private String label;
    private Boolean selected;
    private Double price;
    private Integer quantity;
}

package com.store.pharmacy_service.orders.domain.models;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequest {
    private Long productId;
    private Double unitPrice;
    private Long quantity;
    private Double subTotal;
    private String priceTypeName;
    private Long totalQuantity;
}

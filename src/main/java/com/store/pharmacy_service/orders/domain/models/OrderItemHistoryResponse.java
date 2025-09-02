package com.store.pharmacy_service.orders.domain.models;


import com.store.pharmacy_service.products.domain.DTOs.ProductResponse;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemHistoryResponse {
    private Long id;
    private ProductResponse product;
    private Double unitPrice;
    private Long quantity;
    private Double subTotal;
    private Date orderDate;
}

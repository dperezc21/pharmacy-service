package com.store.pharmacy_service.orders.domain.models;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    private Date date;
    private List<OrderItemRequest> orderItems;
}

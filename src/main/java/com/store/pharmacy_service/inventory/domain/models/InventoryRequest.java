package com.store.pharmacy_service.inventory.domain.models;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
    private Long productId;
    private Long quantity;
    private Date date;

}

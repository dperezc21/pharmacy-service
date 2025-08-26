package com.store.pharmacy_service.inventory.utils;

import com.store.pharmacy_service.inventory.domain.models.InventoryRequest;
import com.store.pharmacy_service.orders.domain.entities.OrderItem;

import java.util.Date;
import java.util.List;

public class MapInventory {

    public static List<InventoryRequest> mapInventoryRequest(List<OrderItem> orderItemList) {
        return orderItemList.stream().map(orderItem1 -> {
            InventoryRequest inventory = new InventoryRequest();
            inventory.setDate(new Date());
            inventory.setQuantity(orderItem1.getQuantity());
            inventory.setProductId(orderItem1.getProduct().getId());
            return inventory;
        }).toList();
    }
}

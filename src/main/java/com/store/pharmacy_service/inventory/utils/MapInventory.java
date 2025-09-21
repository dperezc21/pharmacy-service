package com.store.pharmacy_service.inventory.utils;

import com.store.pharmacy_service.inventory.domain.entities.Inventory;
import com.store.pharmacy_service.inventory.domain.models.InventoryRequest;
import com.store.pharmacy_service.inventory.domain.models.InventoryResponse;
import com.store.pharmacy_service.orders.domain.entities.OrderItem;

import java.util.List;

public class MapInventory {

    public static List<InventoryRequest> mapInventoryRequest(List<OrderItem> orderItemList) {
        return orderItemList.stream().map(orderItem1 -> {
            InventoryRequest inventory = new InventoryRequest();
            inventory.setDate(orderItem1.getDate());
            inventory.setQuantity(orderItem1.getTotalQuantity());
            inventory.setProductId(orderItem1.getProduct().getId());
            return inventory;
        }).parallel().toList();
    }

    public static InventoryResponse mapInventoryResponse(Inventory inventory) {

        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setInventoryId(inventory.getId());
        inventoryResponse.setDate(inventory.getDate());
        inventoryResponse.setQuantity(inventory.getQuantity());
        inventoryResponse.setProductId(inventory.getProduct().getId());
        return inventoryResponse;

    }
}

package com.store.pharmacy_service.inventory.infrastructure;

import com.store.pharmacy_service.inventory.application.InventoryService;
import com.store.pharmacy_service.inventory.domain.entities.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired private InventoryService inventoryService;

    @GetMapping("/{sku}")
    public boolean isInStock(@PathVariable String sku) {
        return this.inventoryService.isInStock(sku);
    }

    @GetMapping
    public List<Inventory> getAllInventories() {
        return this.inventoryService.inventories();
    }

    @GetMapping("/{productId}/{quantity}")
    public Long verifyProductStock(@PathVariable Long productId, @PathVariable Long quantity) {
        return this.inventoryService.verifyStockValid(productId, quantity);
    }


}

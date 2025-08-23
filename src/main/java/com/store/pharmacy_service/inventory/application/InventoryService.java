package com.store.pharmacy_service.inventory.application;

import com.store.pharmacy_service.inventory.domain.entities.Inventory;
import com.store.pharmacy_service.inventory.domain.models.InventoryRequest;
import com.store.pharmacy_service.inventory.domain.repositories.InventoryRepository;
import com.store.pharmacy_service.products.application.ProductService;
import com.store.pharmacy_service.products.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class InventoryService {

    @Autowired private InventoryRepository inventoryRepository;
    @Autowired private ProductService productService;

    public boolean isInStock(String sku) {
        Inventory inventoryInStock = inventoryRepository.findByProductSku(sku);
        return Objects.nonNull(inventoryInStock) && Objects.nonNull(inventoryInStock.getId()) && inventoryInStock.getQuantity() > 0;
    }

    public Long verifyStockValid(Long productId, Long quantity) {
        Product product = productService.findProductById(productId);
        if(product == null) return 0L;
        Inventory productInStock = inventoryRepository.findByProductId(productId);
        return Objects.nonNull(productInStock.getId()) ? productInStock.getQuantity() - quantity : 0L;
    }

    public List<Inventory> inventories() {
        return Streamable.of(this.inventoryRepository.findAll()).toList();
    }

    public void saveProductsInInventory(List<InventoryRequest> inventoryRequest) {
        inventoryRepository.saveAll(this.mapToInventoryList(inventoryRequest));
    }

    public Inventory getInventoryByProductId(Long product) {
        return inventoryRepository.findByProductId(product);
    }

    private List<Inventory> mapToInventoryList(List<InventoryRequest> inventoryRequest) {
        return inventoryRequest.stream().map(inventoryRequest1 -> {
            Inventory inventory = getInventoryByProductId(inventoryRequest1.getProductId());
            inventory.setDate(new Date());
            inventory.setQuantity(inventoryRequest1.getQuantity());
            inventory.setProduct(inventory.getProduct());
            return inventory;
        }).toList();
    }
}

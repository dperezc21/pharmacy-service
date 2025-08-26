package com.store.pharmacy_service.inventory.application;

import com.store.pharmacy_service.inventory.domain.entities.Inventory;
import com.store.pharmacy_service.inventory.domain.models.InventoryRequest;
import com.store.pharmacy_service.inventory.domain.models.InventoryResponse;
import com.store.pharmacy_service.inventory.domain.repositories.InventoryRepository;
import com.store.pharmacy_service.inventory.utils.MapInventory;
import com.store.pharmacy_service.products.application.ProductService;
import com.store.pharmacy_service.products.domain.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

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

    public List<InventoryResponse> getInventories() {
        List<Inventory> inventories = Streamable.of(this.inventoryRepository.findAll()).toList();
        return inventories.stream().map(MapInventory::mapInventoryResponse).parallel().toList();
    }

    public void saveProductsInInventory(List<InventoryRequest> inventoryRequest) {
        inventoryRepository.saveAll(this.mapToBuysInventoryRequest(inventoryRequest));
    }

    public void saveProductsInInventoryFromSale(List<InventoryRequest> inventoryRequest) {
        inventoryRepository.saveAll(this.mapToSaleInventoryList(inventoryRequest));
    }

    public Inventory getInventoryByProductId(Long product) {
        return inventoryRepository.findByProductId(product);
    }

    public List<Inventory> mapToBuysInventoryRequest(List<InventoryRequest> inventoryRequests) {
        return inventoryRequests.stream().map(inventoryRequest1 -> {
            Inventory inventory = getInventoryByProductId(inventoryRequest1.getProductId());
            if(inventory == null) {
                inventory = new Inventory();
                Product product = this.productService.findProductById(inventoryRequest1.getProductId());
                inventory.setProduct(product);
            }
            Long quantity = Objects.nonNull(inventory.getQuantity()) ? inventory.getQuantity() + inventoryRequest1.getQuantity() : inventoryRequest1.getQuantity();
            inventory.setDate(inventoryRequest1.getDate());
            inventory.setQuantity(quantity);
            return inventory;
        }).parallel().toList();
    }

    public List<Inventory> mapToSaleInventoryList(List<InventoryRequest> inventoryRequests) {
        return inventoryRequests.stream().map(inventoryRequest1 -> {
            Inventory inventory = getInventoryByProductId(inventoryRequest1.getProductId());
            if(inventory == null) {
                inventory = new Inventory();
                Product product = this.productService.findProductById(inventoryRequest1.getProductId());
                inventory.setProduct(product);
            }
            Long quantity = Objects.nonNull(inventory.getQuantity()) ? inventory.getQuantity() - inventoryRequest1.getQuantity() : inventoryRequest1.getQuantity();
            inventory.setDate(inventoryRequest1.getDate());
            inventory.setQuantity(quantity);
            return inventory;
        }).parallel().toList();
    }
}

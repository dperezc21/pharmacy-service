package com.store.pharmacy_service.inventory.domain.repositories;

import com.store.pharmacy_service.inventory.domain.entities.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    Inventory findByProductSku(String sku);

    Inventory findByProductId(Long productId);

}

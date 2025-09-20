package com.store.pharmacy_service.orders.domain.repositories;

import com.store.pharmacy_service.orders.domain.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    List<OrderItem> getOrderItemByOrderType(String orderType);
}

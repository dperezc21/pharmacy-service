package com.store.pharmacy_service.orders.domain.repositories;

import com.store.pharmacy_service.orders.domain.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> { }

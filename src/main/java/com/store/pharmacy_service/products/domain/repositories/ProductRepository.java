package com.store.pharmacy_service.products.domain.repositories;

import com.store.pharmacy_service.products.domain.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {}

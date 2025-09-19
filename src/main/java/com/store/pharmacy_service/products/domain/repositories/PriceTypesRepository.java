package com.store.pharmacy_service.products.domain.repositories;

import com.store.pharmacy_service.products.domain.entities.PriceType;
import org.springframework.data.repository.CrudRepository;

public interface PriceTypesRepository extends CrudRepository<PriceType, Long> { }

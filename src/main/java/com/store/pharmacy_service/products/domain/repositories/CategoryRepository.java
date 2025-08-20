package com.store.pharmacy_service.products.domain.repositories;

import com.store.pharmacy_service.products.domain.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}

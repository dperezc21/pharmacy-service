package com.store.pharmacy_service.user.domain.repositories;

import com.store.pharmacy_service.user.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity getUserByUserName(String username);
}

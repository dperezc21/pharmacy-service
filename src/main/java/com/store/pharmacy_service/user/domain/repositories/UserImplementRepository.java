package com.store.pharmacy_service.user.domain.repositories;

import com.store.pharmacy_service.user.domain.entities.UserEntity;

import java.util.List;

public interface UserImplementRepository {
    UserEntity getUserByUserName(String userName);
    UserEntity getUserById(Long userId);
    UserEntity saveUser(UserEntity userEntity);
    void deleteUse(UserEntity userEntity);
    List<UserEntity> getAllUsers();
}

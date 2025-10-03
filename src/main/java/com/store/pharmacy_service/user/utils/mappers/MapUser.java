package com.store.pharmacy_service.user.utils.mappers;

import com.store.pharmacy_service.user.domain.entities.UserEntity;
import com.store.pharmacy_service.user.domain.entities.UserRole;
import com.store.pharmacy_service.user.domain.models.UserResponse;

public class MapUser {

    public static UserResponse mapToUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .userName(userEntity.getUserName())
                .fullName(userEntity.getName())
                .role(UserRole.valueOf(userEntity.getRole()))
                .build();
    }
}

package com.store.pharmacy_service.user.domain.models;


import com.store.pharmacy_service.user.domain.entities.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String fullName;

    private String userName;

    private String password;

    private UserRole role;
}

package com.store.pharmacy_service.user.application;

import com.store.pharmacy_service.user.domain.entities.UserEntity;
import com.store.pharmacy_service.user.domain.entities.UserRole;
import com.store.pharmacy_service.user.domain.exceptions.UserNotFoundException;
import com.store.pharmacy_service.user.domain.models.UserRequest;
import com.store.pharmacy_service.user.domain.models.UserResponse;
import com.store.pharmacy_service.user.domain.repositories.UserImplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired private UserImplementRepository userImplService;

    public UserResponse saveUser(UserRequest userRequest) {
        UserEntity userToSave = UserEntity.builder()
                .userName(userRequest.getUserName())
                .name(userRequest.getFullName())
                .password(BCryptUserPassword.encrypt(userRequest.getPassword()))
                .role(userRequest.getRole().name())
                .build();
        UserEntity userSaved = userImplService.saveUser(userToSave);
        return mapToUserResponse(userSaved);
    }

    public UserResponse getUserByName(String userName) throws UserNotFoundException {
        UserEntity getUserByUserName = this.userImplService.getUserByUserName(userName);
        if(Objects.isNull(getUserByUserName)) throw new UserNotFoundException("user no exists with this user name");
        return mapToUserResponse(getUserByUserName);
    }

    public UserResponse editUser(Long userId, UserRequest userRequest) throws UserNotFoundException {
        UserEntity findUserById = this.userImplService.getUserById(userId);
        if(Objects.isNull(findUserById)) throw new UserNotFoundException("User no found");
        findUserById.setUserName(userRequest.getUserName());
        findUserById.setName(userRequest.getFullName());
        findUserById.setRole(userRequest.getRole().name());
        if(Objects.nonNull(userRequest.getPassword()))
            findUserById.setPassword(BCryptUserPassword.encrypt(userRequest.getPassword()));
        this.userImplService.saveUser(findUserById);
        return this.mapToUserResponse(findUserById);
    }

    public List<UserResponse> getAllUsers() {
        List<UserEntity> users = this.userImplService.getAllUsers();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    public void deleteUserById(Long userId) throws UserNotFoundException {
        UserEntity findUserToDelete = this.userImplService.getUserById(userId);
        if(Objects.isNull(findUserToDelete)) throw new UserNotFoundException("User no found");
        this.userImplService.deleteUse(findUserToDelete);
    }

    private UserResponse mapToUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .userName(userEntity.getUserName())
                .fullName(userEntity.getName())
                .role(UserRole.valueOf(userEntity.getRole()))
                .build();
    }
}

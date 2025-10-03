package com.store.pharmacy_service.user.application;

import com.store.pharmacy_service.user.domain.entities.UserEntity;
import com.store.pharmacy_service.user.domain.exceptions.UserNotFoundException;
import com.store.pharmacy_service.user.domain.models.UserRequest;
import com.store.pharmacy_service.user.domain.models.UserResponse;
import com.store.pharmacy_service.user.domain.repositories.UserImplementRepository;
import com.store.pharmacy_service.user.utils.mappers.MapUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired private UserImplementRepository userImplService;

    public UserResponse editUser(Long userId, UserRequest userRequest) throws UserNotFoundException {
        UserEntity findUserById = this.userImplService.getUserById(userId);
        if(Objects.isNull(findUserById)) throw new UserNotFoundException("User no found");
        findUserById.setUserName(userRequest.getUserName());
        findUserById.setName(userRequest.getFullName());
        findUserById.setRole(userRequest.getRole().name());
        if(Objects.nonNull(userRequest.getPassword()))
            findUserById.setPassword(BCryptUserPassword.encrypt(userRequest.getPassword()));
        this.userImplService.saveUser(findUserById);
        return MapUser.mapToUserResponse(findUserById);
    }

    public List<UserResponse> getAllUsers() {
        List<UserEntity> users = this.userImplService.getAllUsers();
        return users.stream().map(MapUser::mapToUserResponse).toList();
    }

    public void deleteUserById(Long userId) throws UserNotFoundException {
        UserEntity findUserToDelete = this.userImplService.getUserById(userId);
        if(Objects.isNull(findUserToDelete)) throw new UserNotFoundException("User no found");
        this.userImplService.deleteUse(findUserToDelete);
    }
}

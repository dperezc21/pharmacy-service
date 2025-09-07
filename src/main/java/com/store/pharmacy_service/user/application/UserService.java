package com.store.pharmacy_service.user.application;

import com.store.pharmacy_service.user.domain.entities.UserEntity;
import com.store.pharmacy_service.user.domain.entities.UserRole;
import com.store.pharmacy_service.user.domain.exceptions.UserNotFoundException;
import com.store.pharmacy_service.user.domain.models.UserRequest;
import com.store.pharmacy_service.user.domain.models.UserResponse;
import com.store.pharmacy_service.user.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public UserResponse saveUser(UserRequest userRequest) {
        UserEntity userToSave = UserEntity.builder()
                .userName(userRequest.getUserName())
                .name(userRequest.getFullName())
                .password(BCryptUserPassword.encrypt(userRequest.getPassword()))
                .role(userRequest.getRole().name())
                .build();
        UserEntity userSaved = userRepository.save(userToSave);
        return mapToUserResponse(userSaved);
    }

    public UserResponse login(String userName, String password) throws UserNotFoundException {
        UserEntity getUserByUserName = this.userRepository.getUserByUserName(userName);
        if(Objects.isNull(getUserByUserName)) throw new UserNotFoundException("user no exists with this user name");
        boolean passwordMatch = BCryptUserPassword.match(password, getUserByUserName.getPassword());
        if (!passwordMatch) throw new UserNotFoundException("password incorrect");
        return mapToUserResponse(getUserByUserName);
    }

    public UserResponse editUser(Long userId, UserRequest userRequest) throws UserNotFoundException {
        UserEntity findUserById = this.userRepository.findById(userId).orElse(null);
        if(Objects.isNull(findUserById)) throw new UserNotFoundException("User no found");
        findUserById.setUserName(userRequest.getUserName());
        findUserById.setName(userRequest.getFullName());
        findUserById.setRole(userRequest.getRole().name());
        if(Objects.nonNull(userRequest.getPassword()))
            findUserById.setPassword(BCryptUserPassword.encrypt(userRequest.getPassword()));
        this.userRepository.save(findUserById);
        return this.mapToUserResponse(findUserById);
    }

    public List<UserResponse> getAllUsers() {
        List<UserEntity> users = Streamable.of(this.userRepository.findAll()).toList();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    public void deleteUserById(Long userId) throws UserNotFoundException {
        UserEntity findUserToDelete = this.userRepository.findById(userId).orElse(null);
        if(Objects.isNull(findUserToDelete)) throw new UserNotFoundException("User no found");
        this.userRepository.delete(findUserToDelete);
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

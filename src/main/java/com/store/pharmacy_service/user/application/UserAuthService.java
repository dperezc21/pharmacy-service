package com.store.pharmacy_service.user.application;

import com.store.pharmacy_service.configs.JwtUtil;
import com.store.pharmacy_service.user.domain.entities.UserEntity;
import com.store.pharmacy_service.user.domain.exceptions.UserNotFoundException;
import com.store.pharmacy_service.user.domain.models.UserRequest;
import com.store.pharmacy_service.user.domain.models.UserResponse;
import com.store.pharmacy_service.user.domain.repositories.UserImplementRepository;
import com.store.pharmacy_service.user.utils.mappers.MapUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired private UserImplementRepository userImplService;

    public UserResponse login(String userName, String password) throws UserNotFoundException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        UserResponse userResponse = this.getUserByName(userName);

        final String jwt = jwtUtil.generateToken(userResponse.getUserName());
        userResponse.setToken(jwt);
        return userResponse;
    }

    public UserResponse getUserByName(String userName) throws UserNotFoundException {
        UserEntity getUserByUserName = this.userImplService.getUserByUserName(userName);
        if(Objects.isNull(getUserByUserName)) throw new UserNotFoundException("user no exists with this user name");
        return MapUser.mapToUserResponse(getUserByUserName);
    }

    public UserResponse registerUser(UserRequest userRequest) {
        UserEntity userToSave = UserEntity.builder()
                .userName(userRequest.getUserName())
                .name(userRequest.getFullName())
                .password(BCryptUserPassword.encrypt(userRequest.getPassword()))
                .role(userRequest.getRole().name())
                .build();
        UserEntity userSaved = userImplService.saveUser(userToSave);
        return MapUser.mapToUserResponse(userSaved);
    }

    public Boolean verifyToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Eliminar "Bearer "
            boolean tokenIsNotValid = this.jwtUtil.tokenIsNotValid(token);
            return !token.isEmpty() && !tokenIsNotValid;
        }
        return false;
    }
}

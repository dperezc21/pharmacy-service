package com.store.pharmacy_service.user.infrastructure.controllers;

import com.store.pharmacy_service.user.application.UserAuthService;
import com.store.pharmacy_service.user.domain.exceptions.UserNotFoundException;
import com.store.pharmacy_service.user.domain.models.UserRequest;
import com.store.pharmacy_service.user.domain.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(this.userAuthService.registerUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse;
        try {
            userResponse = this.userAuthService.login(userRequest.getUserName(), userRequest.getPassword());
        } catch (BadCredentialsException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body("Credenciales incorrectas " + e.getMessage());
        }
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> getData(@RequestHeader("Authorization") String authorizationHeader) {
        Boolean tokenValid = userAuthService.verifyToken(authorizationHeader);
        if(tokenValid) return ResponseEntity.status(200).body(true);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    }
}

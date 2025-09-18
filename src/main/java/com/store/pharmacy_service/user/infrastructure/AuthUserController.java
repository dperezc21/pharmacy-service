package com.store.pharmacy_service.user.infrastructure;

import com.store.pharmacy_service.configs.JwtUtil;
import com.store.pharmacy_service.user.application.UserService;
import com.store.pharmacy_service.user.domain.exceptions.UserNotFoundException;
import com.store.pharmacy_service.user.domain.models.UserRequest;
import com.store.pharmacy_service.user.domain.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(this.userService.saveUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPassword()));
            userResponse = this.userService.getUserByName(userRequest.getUserName());

        } catch (BadCredentialsException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body("Credenciales incorrectas " + e.getMessage());
        }

        final String jwt = jwtUtil.generateToken(userResponse.getUserName());
        userResponse.setToken(jwt);

        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long userId) {
        UserResponse userResponse;
        try {
            userResponse = this.userService.editUser(userId, userRequest);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> allUsers() {
        List<UserResponse> userResponse;
        try {
            userResponse = this.userService.getAllUsers();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
        try {
            this.userService.deleteUserById(userId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(false);
        }
        return ResponseEntity.status(200).body(true);
    }
}

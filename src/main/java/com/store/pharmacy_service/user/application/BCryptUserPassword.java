package com.store.pharmacy_service.user.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptUserPassword {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    public static Boolean match(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
}

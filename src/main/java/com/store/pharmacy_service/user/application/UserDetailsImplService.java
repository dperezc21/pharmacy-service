package com.store.pharmacy_service.user.application;

import com.store.pharmacy_service.user.domain.entities.UserEntity;
import com.store.pharmacy_service.user.domain.exceptions.UserNotFoundException;
import com.store.pharmacy_service.user.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsImplService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity usuario = userRepository.getUserByUserName(username);

        if(usuario == null) try {
            throw new UserNotFoundException("Usuario no encontrado");
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new User(
                usuario.getUserName(),
                usuario.getPassword(),
                new ArrayList<>()
        );
    }
}


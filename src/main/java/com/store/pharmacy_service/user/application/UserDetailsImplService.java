package com.store.pharmacy_service.user.application;

import com.store.pharmacy_service.user.domain.entities.UserEntity;
import com.store.pharmacy_service.user.domain.exceptions.UserNotFoundException;
import com.store.pharmacy_service.user.domain.repositories.UserImplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsImplService implements UserDetailsService {
    @Autowired
    private UserImplementRepository userImplementRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity findUserByName = userImplementRepository.getUserByUserName(username);

        if(findUserByName == null) try {
            throw new UserNotFoundException("Usuario no encontrado");
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new User(
                findUserByName.getUserName(),
                findUserByName.getPassword(),
                new ArrayList<>()
        );
    }
}


package com.store.pharmacy_service.user.infrastructure.implementations;

import com.store.pharmacy_service.user.domain.entities.UserEntity;
import com.store.pharmacy_service.user.domain.repositories.UserImplementRepository;
import com.store.pharmacy_service.user.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImplService implements UserImplementRepository {

    @Autowired private UserRepository userRepository;

    @Override
    public UserEntity getUserByUserName(String userName) {
        return this.userRepository.getUserByUserName(userName);
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    @Override
    public void deleteUse(UserEntity userEntity) {
        this.userRepository.delete(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return Streamable.of(this.userRepository.findAll()).toList();
    }
}

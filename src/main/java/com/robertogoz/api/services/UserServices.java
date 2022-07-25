package com.robertogoz.api.services;

import com.robertogoz.api.entities.User;
import com.robertogoz.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    final UserRepository _userRepository;

    public UserServices(UserRepository userRepository) {
        this._userRepository = userRepository;
    }

    public List<User> findAll() {
        return _userRepository.findAll();
    }
}

package com.robertogoz.api.services;

import com.robertogoz.api.entities.User;
import com.robertogoz.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServices {
    final UserRepository _userRepository;

    public UserServices(UserRepository userRepository) {
        this._userRepository = userRepository;
    }

    public List<User> findAll() {
        return _userRepository.findAll();
    }

    @Transactional
    public User save(User user) {
        return _userRepository.save(user);
    }

    public Optional<User> findById(UUID id) {
        return _userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) { return _userRepository.findByEmail(email); }

    @Transactional
    public void delete(User user) {
        _userRepository.delete(user);
    }
}

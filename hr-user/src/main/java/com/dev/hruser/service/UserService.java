package com.dev.hruser.service;

import com.dev.hruser.entities.User;
import com.dev.hruser.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    
    private UserRepository userRepository;
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

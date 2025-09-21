package com.inovace.spring_security.services;

import com.inovace.spring_security.entities.User;
import com.inovace.spring_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(String username, String password,String roles,Boolean active) {

        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(username, hashedPassword,roles,active);
        return userRepository.save(user);
    }
}
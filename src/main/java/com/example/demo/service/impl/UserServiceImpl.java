package com.example.demo.service.impl;

import java.util.List;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) throws Exception {
        // Your registration logic here
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new Exception("User not found with email: " + email));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
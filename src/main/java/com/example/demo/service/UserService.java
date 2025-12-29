package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {

    User register(User user);

    User findByEmail(String email);
    
    // ADD THESE 3 METHODS ONLY
    User saveUser(User user);
    
    List<User> getAllUsers();
    
    User getUserById(Long id);
}
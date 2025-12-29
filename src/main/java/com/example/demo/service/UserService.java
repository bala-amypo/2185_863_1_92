package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {

    User register(User user);
    
    User saveUser(User user);  // Add this
    
    List<User> getAllUsers();  // Add this
    
    User getUserById(Long id);  // Add this
    
    User findByEmail(String email);
}
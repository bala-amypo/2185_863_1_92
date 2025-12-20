package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {

    User register(User user) throws Exception;

    User findByEmail(String email) throws Exception;

    List<User> getUsers();   // ✅ ADD THIS
}

package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    // Constructor - this should have no return type
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getUserById(id);
    }
}
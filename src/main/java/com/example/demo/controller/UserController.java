package com.example.demo.controller;
import java.util.List;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

  @GetMapping("/users")
public List<User> getUsers() {
    try {
        return userService.getUsers();
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to fetch users");
    }
}

}

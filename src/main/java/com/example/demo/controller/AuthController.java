package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil = new JwtUtil();

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        User dbUser = userService.findByEmail(user.getEmail());

        String token = jwtUtil.generateToken(
                dbUser.getId(),
                dbUser.getEmail(),
                dbUser.getRole()
        );

        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("email", dbUser.getEmail());
        res.put("role", dbUser.getRole());
        return res;
    }
}

package com.example.bookapp.controller;

import com.example.bookapp.model.User;
import com.example.bookapp.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Mono<User> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Mono<User> login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }
}

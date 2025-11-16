package com.example.bookapp.service;

import org.springframework.stereotype.Service;

import com.example.bookapp.model.User;
import com.example.bookapp.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> register(User user) {
        return userRepository.save(user);
    }


    public Mono<User> login(String username, String password) {
        return userRepository.findByUsername(username)
                .flatMap(user -> {
                    if (user.getPassword().equals(password)) {
                        return Mono.just(user);
                    }
                    return Mono.error(new RuntimeException("Invalid password"));
                });
    }
}

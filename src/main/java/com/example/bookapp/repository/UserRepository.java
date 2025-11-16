package com.example.bookapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.bookapp.model.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByUsername(String username);
}

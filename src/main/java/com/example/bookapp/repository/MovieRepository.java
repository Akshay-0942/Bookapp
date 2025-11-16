package com.example.bookapp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.bookapp.model.Movie;

import reactor.core.publisher.Flux;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
	
	Flux<Movie> findByTitleContainingIgnoreCase(String title);

	Flux<Movie> findByRatingBetween(Double min, Double max);

}

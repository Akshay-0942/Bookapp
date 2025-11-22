package com.example.bookapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bookapp.model.Movie;
import com.example.bookapp.repository.MovieRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void clean() {
        movieRepository.deleteAll().block();
    }

    @Test
    void testAddMovie() {
        Movie movie = new Movie();
        movie.setTitle("KGF");
        movie.setRating(9.5);

        Mono<Movie> result = movieService.addMovie(movie);

        StepVerifier.create(result)
                .expectNextMatches(m -> m.getTitle().equals("KGF"))
                .verifyComplete();
    }
}

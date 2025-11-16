package com.example.bookapp.controller;

import com.example.bookapp.model.Movie;
import com.example.bookapp.service.MovieService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

//    @PostMapping
//    public Mono<Movie> addMovie(@RequestBody Movie movie) {
//        return movieService.addMovie(movie);
//    }

    @GetMapping
    public Flux<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Mono<Movie> getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

//    @PutMapping("/{id}")
//    public Mono<Movie> updateMovie(@PathVariable String id, @RequestBody Movie movie) {
//        return movieService.updateMovie(id, movie);
//    }

    
    
    @GetMapping("/search")
    public Flux<Movie> searchMovies(@RequestParam String title) {
        return movieService.searchMovies(title);
    }

    
    @PutMapping("/{id}")
    public Mono<?> updateMovie(
            @RequestHeader("role") String role,
            @PathVariable String id,
            @RequestBody Movie movie) {

        if (!role.equals("ADMIN")) {
            return Mono.error(new RuntimeException("Only ADMIN can update movies"));
        }

        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMovie(@PathVariable String id) {
        return movieService.deleteMovie(id);
    }
    
    @GetMapping("/filter")
    public Flux<Movie>filtermovies(
    		
    		@RequestParam Double Min,@RequestParam Double Max,@RequestParam  Integer Top)
    {
    	return movieService.filtermovies(Min,Max,Top);
    }
    
    @PostMapping
    public Mono<?> addMovie(@RequestHeader("role") String role, @RequestBody Movie movie) {
        if (!role.equals("ADMIN")) {
            return Mono.error(new RuntimeException("Only ADMIN can add movies"));
        }
        return movieService.addMovie(movie);
    }
    
    

}

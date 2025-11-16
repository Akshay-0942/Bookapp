package com.example.bookapp.service;

import org.springframework.stereotype.Service;

import com.example.bookapp.model.Movie;
import com.example.bookapp.repository.MovieRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Mono<Movie> addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Mono<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    public Mono<Movie> updateMovie(String id, Movie updatedMovie) {
        return movieRepository.findById(id)
                .flatMap(existing -> {
                    existing.setTitle(updatedMovie.getTitle());
                    existing.setGenre(updatedMovie.getGenre());
                    existing.setDescription(updatedMovie.getDescription());
                    existing.setRating(updatedMovie.getRating());
                    return movieRepository.save(existing);
                });
    }

    
    public Flux<Movie> searchMovies(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public Mono<Void> deleteMovie(String id) {
        return movieRepository.deleteById(id);
    }

	public Flux<Movie> filtermovies(Double min, Double max, Integer top) {
		if(top!=null)
		{
			return movieRepository.findAll()
					.sort((m1, m2)-> Double.compare(m2.getRating(),m1.getRating())) 
					.take(top);
		}
		
		if(min!=null && max !=null)
		{
			return movieRepository.findByRatingBetween(min,max);
					}
		
		// TODO Auto-generated method stub
		return  movieRepository.findAll();
	}
}

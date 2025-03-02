package com.backendengineer.springfordemo.Service;

import com.backendengineer.springfordemo.Entity.Movie;
import com.backendengineer.springfordemo.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@EnableCaching
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    @CacheEvict(value = "MovieList", allEntries = true)
    public Movie addMovie(Movie movie) {
        log.info("Movie added successfully");
        return movieRepository.save(movie);
    }

    @Cacheable(value = "MovieList")
    public List<Movie> findallmovie() {
        log.info("All movies retrieved successfully");
        return movieRepository.findAll();
    }

    @Cacheable(key = "#id", value = "Movie")
    public Optional<Movie> findbyid(Long id) {
        log.info("Movie with id {} retrieved successfully", id);
        return movieRepository.findById(id);
    }

    @CacheEvict(key = "#id", value = {"Movie", "MovieList"}, allEntries = true)
    public String deletebyid(Long id) {
        log.info("Movie with id {} deleted successfully", id);
        movieRepository.deleteById(id);
        return "Movie with id " + id + " deleted successfully";
    }

    @CachePut(key = "#id", value = "Movie")
    @CacheEvict(value = "MovieList", allEntries = true)
    public Movie updateMovie(Long id, Movie movie) {
        var updatedMovie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    
        if (Objects.nonNull(movie.getTitle()) && !movie.getTitle().isEmpty()) {
            updatedMovie.setTitle(movie.getTitle());
        }
        if (Objects.nonNull(movie.getReleaseDate()) && !movie.getReleaseDate().isEmpty()) {
            updatedMovie.setReleaseDate(movie.getReleaseDate());
        }
        if (Objects.nonNull(movie.getTrailorLink()) && !movie.getTrailorLink().isEmpty()) {
            updatedMovie.setTrailorLink(movie.getTrailorLink());
        }
    
        return movieRepository.save(updatedMovie);
    }
}

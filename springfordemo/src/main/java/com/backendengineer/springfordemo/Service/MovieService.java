package com.backendengineer.springfordemo.Service;

import com.backendengineer.springfordemo.Entity.Movie;
import com.backendengineer.springfordemo.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@EnableCaching
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Movie addMovie(Movie movie) {
        log.info("Movie added successfully");
        return movieRepository.save(movie);
    }
    public List<Movie> findallmovie() {
        log.info("All movies retrieved successfully");
        return movieRepository.findAll();
    }
    @Cacheable(key = "id", value = "Movie")
    public Optional<Movie> findbyid(Long id) {
        log.info("Movie with id {} retrieved successfully", id);
        return movieRepository.findById(id);
    }

    public String deletebyid(Long id) {
        log.info("Movie with id {} deleted successfully", id);
        movieRepository.deleteById(id);
        return "Movie with id "+ id +" deleted successfully";
    }
}

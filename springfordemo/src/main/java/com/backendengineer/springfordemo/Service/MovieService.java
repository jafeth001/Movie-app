package com.backendengineer.springfordemo.Service;

import com.backendengineer.springfordemo.Entity.Movie;
import com.backendengineer.springfordemo.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    public List<Movie> findallmovie() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findbyid(Long id) {
        return movieRepository.findById(id);
    }

    public void deletebyid(Long id) {
        movieRepository.deleteById(id);
    }
}

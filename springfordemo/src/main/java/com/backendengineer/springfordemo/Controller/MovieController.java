package com.backendengineer.springfordemo.Controller;

import com.backendengineer.springfordemo.Entity.Movie;
import com.backendengineer.springfordemo.Service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> findallmovie() {
        return ResponseEntity.ok(movieService.findallmovie());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> findbyid(@PathVariable("id") Long id) {
        return ResponseEntity.ok(movieService.findbyid(id));
    }

    @DeleteMapping("/{id}")
    public String deletebyid(@PathVariable("id") Long id) {
        movieService.deletebyid(id);
        return "Movie deleted successfully";
    }
}
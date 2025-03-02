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

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id, @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.updateMovie(id,movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletebyid(@PathVariable("id") Long id) {
        return ResponseEntity.ok(movieService.deletebyid(id));
    }
}
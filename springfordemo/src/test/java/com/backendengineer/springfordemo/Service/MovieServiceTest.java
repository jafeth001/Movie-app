package com.backendengineer.springfordemo.Service;

import com.backendengineer.springfordemo.Entity.Movie;
import com.backendengineer.springfordemo.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class MovieServiceTest {
    @MockBean
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;
    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = Movie.builder()
                .id(1L)
                .title("Inception")
                .description("A mind-bending thriller")
                .description("test")
                .poster("poster.jpg")
                .build();
    }

    @DisplayName("test for saving movie")
    @Test
    void whenAddMovie_thenReturnMovie() {
        var newMovie = Movie.builder()
                .id(2L)
                .title("Ramster")
                .description("A ramster-bending thriller")
                .description("test")
                .poster("poster.jpg")
                .build();
        var movieRepo = Mockito.when(movieRepository.save(newMovie))
                .thenReturn(newMovie);
        var savedMovie = movieService.addMovie(newMovie);
        log.info("savedMovie: {}", savedMovie);
        assertThat (savedMovie).isNotNull();
        assert("Ramster".equals(savedMovie.getTitle()));

    }
    @DisplayName("test for finding all movies")
    @Test
    void whenFindAll_thenReturnList() {
        var newMovie = Movie.builder()
                .id(2L)
                .title("Ramster")
                .description("A ramster-bending thriller")
                .description("test")
                .poster("poster.jpg")
                .build();
        Mockito.when(movieRepository.findAll())
                .thenReturn(List.of(movie,newMovie));
        var list = movieService.findallmovie();
        log.info("Movie list: {}", list);
        assertThat (list.size()).isEqualTo(2);
    }

    @Test
    void findbyid() {
    }

    @Test
    void deletebyid() {
    }
}
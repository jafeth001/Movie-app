package com.backendengineer.springfordemo.Repository;

import com.backendengineer.springfordemo.Entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Slf4j
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TestEntityManager entityManager;

    private Movie movie;
    @BeforeEach
    void setUp() {
        movie = Movie.builder()
                .title("Inception")
                .description("A mind-bending thriller")
                .description("test")
                .poster("poster.jpg")
                .build();
        entityManager.persist(movie);
    }

    @DisplayName("Test find by id movies")
    @Test
    public void whenFindById_thenReturnMovie() {
        Long id = 1L;
        Optional<Movie> found = movieRepository.findById(movie.getId());
        log.info("Found movie: {}", found);
        assertEquals(movie.getId(),found.get().getId());
        assertEquals(movie.getTitle(), found.get().getTitle());
    }

    @DisplayName("Test update movie")
    @Test
    public void whenUpdateMovie_thenReturnMovie() {
        Long id = 1L;
        var movie = movieRepository.findById(id).get();
        movie.setTitle("Inception 2");
        movieRepository.save(movie);
        log.info("Updated movie: {}", movie);
        assert (movie.getTitle().equals("Inception 2"));
    }

    @DisplayName("Test delete movie")
    @Test
    public void whenDeleteMovie_thenReturnMovie() {
        Long id = 1L;
        movieRepository.deleteById(movie.getId());
        log.info("Deleted movie: {}", movie);
        Optional<Movie> found = movieRepository.findById(id);
        assert (found).isEmpty();
    }
}

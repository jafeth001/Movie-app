package com.backendengineer.springfordemo.Controller;

import com.backendengineer.springfordemo.Entity.Movie;
import com.backendengineer.springfordemo.Service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
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

    @Test
    @DisplayName("Junit test for saving movie")
    void whenAddMovie_thenReturnMovie() throws Exception {
        Mockito.when(movieService.addMovie(movie)).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/movie/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Junit test for finding all movies")
    void whenFindAll_thenReturnList() throws Exception {
        var newMovie = Movie.builder()
                .id(2L)
                .title("Ramster")
                .description("A ramster-bending thriller")
                .description("test")
                .poster("poster.jpg")
                .build();
        Mockito.when(movieService.addMovie(movie)).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(List.of(movie, newMovie))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Junit test for finding movie by id")
    void whenFindById_thenReturnMovie() throws Exception {
        Long movieId = 1L;
        Mockito.when(movieService.findbyid(movieId)).thenReturn(java.util.Optional.ofNullable(movie));
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/{id}", movieId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(movieId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Junit test for deleting movie by id")
    void whenDeleteMovie_thenReturnMovie() throws Exception {
        Long movieId = 1L;
        Mockito.when(movieService.findbyid(movieId)).thenReturn(java.util.Optional.ofNullable(movie));
        mockMvc.perform(MockMvcRequestBuilders.delete("/movie/{id}", movieId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(movieId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}
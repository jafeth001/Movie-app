package com.backendengineer.springfordemo.Controller;

import com.backendengineer.springfordemo.Entity.Movie;
import com.backendengineer.springfordemo.Service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
    void addMovie() throws Exception {
        Mockito.when(movieService.addMovie(movie)).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/movie/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    void findallmovie() {
    }

    @Test
    void findbyid() {
    }

    @Test
    void deletebyid() {
    }
}
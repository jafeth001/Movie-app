package com.backendengineer.springfordemo.Controller;

import com.backendengineer.springfordemo.Service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class MovieControllerTest {
    @Autowired
    private MovieController movieController;
    @MockBean
    private MovieService movieService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void addMovie() {
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
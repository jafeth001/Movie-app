package com.backendengineer.springfordemo.Repository;

import com.backendengineer.springfordemo.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

}

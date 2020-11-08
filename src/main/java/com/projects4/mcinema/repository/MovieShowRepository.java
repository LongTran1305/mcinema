package com.projects4.mcinema.repository;

import com.projects4.mcinema.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieShowRepository extends JpaRepository<MovieShow, Integer> {
    @Query(value = "SELECT s FROM MovieShow s INNER JOIN MovieDetails m ON s.movieid = m.movieid WHERE m.moviename = ?1")
    List<MovieShow> findByMoviename(String string);
}

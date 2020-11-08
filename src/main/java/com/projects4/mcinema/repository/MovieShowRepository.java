package com.projects4.mcinema.repository;

import com.projects4.mcinema.model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieShowRepository extends JpaRepository<MovieShow, Integer> {
    List<MovieShow> findByScreenIdEquals(int screenid);
    List<MovieShow> findByMovieid(int movieid);
}

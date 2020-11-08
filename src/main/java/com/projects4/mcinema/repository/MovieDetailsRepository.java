package com.projects4.mcinema.repository;

import com.projects4.mcinema.model.MovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDetailsRepository extends JpaRepository<MovieDetails, String> {
}

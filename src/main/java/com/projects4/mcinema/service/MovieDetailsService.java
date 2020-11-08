package com.projects4.mcinema.service;

import com.projects4.mcinema.model.FAQs;
import com.projects4.mcinema.model.MovieDetails;
import com.projects4.mcinema.repository.MovieDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieDetailsService {
    @Autowired
    MovieDetailsRepository repo;

    public List<MovieDetails> listAll(){
        return repo.findAll();
    }

    public MovieDetails get(String id){
        return repo.findById(id).get();
    }

    public void save(MovieDetails movie){
        repo.save(movie);
    }

    public Optional<MovieDetails> findById(String id){
        return repo.findById(id);
    }

    public void delete(String id){
        repo.deleteById(id);
    }
}

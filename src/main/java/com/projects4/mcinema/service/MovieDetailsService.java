package com.projects4.mcinema.service;

import com.projects4.mcinema.model.FAQs;
import com.projects4.mcinema.model.MovieDetails;
import com.projects4.mcinema.repository.MovieDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDetailsService {
    @Autowired
    MovieDetailsRepository repo;

    public List<MovieDetails> listAll(){
        return repo.findAll();
    }

    public void save(MovieDetails movie){
        repo.save(movie);
    }

    public MovieDetails get(int movieid){
        return repo.findById(movieid).get();
    }

    public void delete(int id){
        repo.deleteById(id);
    }
}

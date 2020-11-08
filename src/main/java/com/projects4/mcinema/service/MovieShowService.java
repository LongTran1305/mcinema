package com.projects4.mcinema.service;

import com.projects4.mcinema.model.MovieShow;
import com.projects4.mcinema.repository.MovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieShowService {
    @Autowired
    MovieShowRepository repo;

    public void save(MovieShow show){
        repo.save(show);
    }

    public MovieShow get(int id){
        return repo.findById(id).get();
    }

    public void delete(int id){
        repo.deleteById(id);
    }
}

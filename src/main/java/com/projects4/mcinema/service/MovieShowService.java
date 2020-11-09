package com.projects4.mcinema.service;

import com.projects4.mcinema.model.MovieDetails;
import com.projects4.mcinema.model.MovieShow;
import com.projects4.mcinema.model.Seat;
import com.projects4.mcinema.repository.MovieDetailsRepository;
import com.projects4.mcinema.repository.MovieShowRepository;
import com.projects4.mcinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieShowService {
    @Autowired
    MovieShowRepository repo;

    @Autowired
    MovieDetailsRepository repo2;

    @Autowired
    SeatRepository repo3;

    public void init(Seat seat){
        repo3.save(seat);
    }

    public List<MovieDetails> listAll(){
        return (List<MovieDetails>)repo2.findAll();
    }

    public List<MovieShow> findAll() {
        return repo.findAll();
    }

    public MovieShow get(int id){
        return repo.findById(id).get();
    }

    public void save(MovieShow show){
        repo.save(show);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}

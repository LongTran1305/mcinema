package com.projects4.mcinema.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MovieDetails {
    @Id
    private String movieid;

    @Column(length = 50)
    private String moviename;

    @Column(length = 20)
    private  String director;

    private float duration;

    @Column(length = 20)
    private String genre;

    @Column(length = 200)
    private String description;

    private float rating;

    @Column(length = 50)
    private String image;

    public MovieDetails(String moviename, String director, float duration, String genre, String description, float rating, String image) {
        this.moviename = moviename;
        this.director = director;
        this.duration = duration;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.image = image;
    }
}

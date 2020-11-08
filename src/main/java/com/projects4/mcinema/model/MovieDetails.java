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
@Table(name = "MovieDetails")
public class MovieDetails {
    @Id
    private String movieid;
    private String moviename;
    private  String director;
    private float duration;
    private String genre;
    private String description;
    private float rating;
    private String image;
}

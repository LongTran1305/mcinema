package com.projects4.mcinema.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MovieDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieid;
    private String moviename;
    private  String director;
    private float duration;
    private String genre;
    private String description;
    private float rating;
    private String image;
}

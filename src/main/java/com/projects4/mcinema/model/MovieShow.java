package com.projects4.mcinema.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MovieShow")
public class MovieShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movieId")
    private MovieDetails movieid;

    private Time showtime;

    private String ticketprice;
}

package com.projects4.mcinema.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.*;

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
    @JoinColumn(name="movieid")
    private MovieDetails movieid;

    @Column(length = 5)
    private Time showtime;

    @Column(length = 10)
    private String ticketprice;

    @OneToMany(mappedBy = "show")
    private Collection<Seat> seats;

}

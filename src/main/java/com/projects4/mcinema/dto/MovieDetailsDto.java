package com.projects4.mcinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MovieDetailsDto {
    private String movieid;
    private String moviename;
    private  String director;
    private float duration;
    private String genre;
    private String description;
    private float rating;
    private MultipartFile image;
}

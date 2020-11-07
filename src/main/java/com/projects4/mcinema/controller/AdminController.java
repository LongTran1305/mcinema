package com.projects4.mcinema.controller;

import com.projects4.mcinema.model.FAQs;

import com.projects4.mcinema.model.MovieDetails;
import com.projects4.mcinema.repository.MovieDetailsRepository;
import com.projects4.mcinema.service.FAQsService;
import com.projects4.mcinema.service.MovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private FAQsService faqsService;

    @Autowired
    private MovieDetailsService movieService;


//    Other Navigation
    @GetMapping("/admin")
    public String admin(Model model){
        return "admin/index";
    }

    @GetMapping("/404")
    public String error(Model model){
        return "admin/404";
    }

//    FAQs Navigation
    @GetMapping("/viewFAQs")
    public String viewFAQs(Model model){
        List<FAQs> faqsList = faqsService.listAll();
        model.addAttribute("faqsList", faqsList);
        return "admin/viewFAQs";
    }

    @GetMapping("/createFAQ")
    public String createFAQs(Model model){
        FAQs faq = new FAQs();
        model.addAttribute("faq", faq);
        return "admin/createFAQ";
    }

    @PostMapping("/saveFAQ")
    public String saveFAQ(@ModelAttribute("faq") FAQs faq){
        faqsService.save(faq);
        return "redirect:/viewFAQs";
    }

    @GetMapping("/updateFAQ/{id}")
    public ModelAndView updateFAQ(@PathVariable(name = "id") int id){
        ModelAndView mav = new ModelAndView("admin/updateFAQ");
        FAQs faq = faqsService.get(id);
        mav.addObject("faq", faq);
        return mav;
    }

    @GetMapping("/deleteFAQ/{id}")
    public String deleteFAQ(@PathVariable(name = "id") int id){
        faqsService.delete(id);
        return "redirect:/viewFAQs";
    }

//    Movie Details Navigation

    @GetMapping("/createMovie")
    public String createMovie(Model model){
        return "admin/createMovie";
    }

    @GetMapping("/viewMovies")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public String viewMovies(Model model){
        List<MovieDetails> movieList = movieService.listAll();
        model.addAttribute("movieList", movieList);
        return "admin/viewMovies";
    }

    @PostMapping("/saveMovie")
    public String saveMovie(
            @RequestParam("moviename") String moviename,
            @RequestParam("director") String director,
            @RequestParam("duration") float duration,
            @RequestParam("genre") String genre,
            @RequestParam("description") String description,
            @RequestParam("rating") float rating,
            @RequestAttribute("image") MultipartFile image,
            ModelMap model){
        MovieDetails movie = new MovieDetails();
        movie.setMoviename(moviename);
        movie.setDirector(director);
        movie.setDuration(duration);
        movie.setGenre(genre);
        movie.setDescription(description);
        movie.setRating(rating);
        Path path = Paths.get("src/main/resources/static/images/uploads");
        try {
            InputStream inputStream = image.getInputStream();
            Files.copy(inputStream, path.resolve(image.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            movie.setImage(image.getOriginalFilename());
            model.addAttribute("movie", movie);
            movieService.save(movie);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/viewMovies";
    }

    @GetMapping("/movieDetails/getImg/{image}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("image") String image){
        if (!image.equals("") || image != null){
            try{
                Path fileName = Paths.get("src/main/resources/static/images/uploads", image);
                byte[] buffer = Files.readAllBytes(fileName);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .contentType(MediaType.parseMediaType("image/jpg"))
                        .contentType(MediaType.parseMediaType("image/jpeg"))
                        .body(byteArrayResource);
            } catch (Exception e){

            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/movieDetails/{id}")
    public ModelAndView movieDetails(@PathVariable(name = "id") int id){
        ModelAndView mav = new ModelAndView("admin/movieDetails");
        MovieDetails movie = movieService.get(id);
        mav.addObject("movie", movie);
        return mav;
    }

    @GetMapping("/updateMovie/{id}")
    public ModelAndView updateMovie(@PathVariable(name = "id") int id){
        ModelAndView mav = new ModelAndView("admin/updateMovie");
        MovieDetails movie = movieService.get(id);
        mav.addObject("movie", movie);
        return mav;
    }


    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable(name = "id") int id){
        movieService.delete(id);
        return "redirect:/viewMovies";
    }

}

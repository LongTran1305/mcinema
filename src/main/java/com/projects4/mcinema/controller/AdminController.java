package com.projects4.mcinema.controller;

import com.projects4.mcinema.dto.MovieDetailsDto;
import com.projects4.mcinema.model.FAQs;

import com.projects4.mcinema.model.MovieDetails;
import com.projects4.mcinema.service.FAQsService;
import com.projects4.mcinema.service.MovieDetailsService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private FAQsService faqsService;

    @Autowired
    private MovieDetailsService movieService;


//    Other Navigation
    @GetMapping("/admin")
    public String admin(){
        return "admin/index";
    }

    @GetMapping("/404")
    public String error(){
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
    public String createMovie(ModelMap model){
        MovieDetailsDto movieDto = new MovieDetailsDto();
        model.addAttribute("movieDto", movieDto);
        model.addAttribute("action", "/saveMovie");
        return "admin/createMovie";
    }

    @GetMapping("/viewMovies")
    public String viewMovies(Model model){
        List<MovieDetails> movieList = movieService.listAll();
        model.addAttribute("movieList", movieList);
        return "admin/viewMovies";
    }

    @PostMapping("/saveMovie")
    public String saveMovie(ModelMap map, @ModelAttribute("movieDto") MovieDetailsDto movieDto){
        Optional<MovieDetails> optionalMovie = movieService.findById(movieDto.getMovieid());
        MovieDetails movie = null;
        String image = "blankmovie.jpg";
        Path path = Paths.get("src/main/resources/static/images/uploads/");
        if(optionalMovie.isPresent()){
            //update
            if(movieDto.getImage().isEmpty()){
                image = optionalMovie.get().getImage();
            }else {
                try {
                    InputStream inputStream = movieDto.getImage().getInputStream();
                    Files.copy(inputStream, path.resolve(movieDto.getImage().getOriginalFilename()),
                            StandardCopyOption.REPLACE_EXISTING);
                    image = movieDto.getImage().getOriginalFilename().toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            movie = new MovieDetails(movieDto.getMoviename(), movieDto.getDirector(), movieDto.getDuration(),
                    movieDto.getGenre(),movieDto.getDescription(), movieDto.getRating(), image);
        }else{
            //add new
            if(!movieDto.getImage().isEmpty()){
                try {
                    InputStream inputStream = movieDto.getImage().getInputStream();
                    Files.copy(inputStream, path.resolve(movieDto.getImage().getOriginalFilename()),
                            StandardCopyOption.REPLACE_EXISTING);
                    image = movieDto.getImage().getOriginalFilename().toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        movie = new MovieDetails(movieDto.getMovieid(), movieDto.getMoviename(), movieDto.getDirector(), movieDto.getDuration(),
                                movieDto.getGenre(),movieDto.getDescription(), movieDto.getRating(), image);
        movieService.save(movie);
        return "redirect:/viewMovies";
    }

    @GetMapping("/movieDetails/getImg/{image}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("image") String image){
        if (!image.equals("") || image != null){
            try{
                Path fileName = Paths.get("src/main/resources/static/images/uploads/", image);
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
    public ModelAndView movieDetails(@PathVariable(name = "id") String id){
        ModelAndView mav = new ModelAndView("admin/movieDetails");
        Optional<MovieDetails> movieOptional = movieService.findById(id);
        MovieDetails movie = movieOptional.get();
        mav.addObject("movie", movie);
        return mav;
    }

    @RequestMapping("/updateMovie/{id}")
    public String updateMovie(ModelMap model, @PathVariable("id") String id){
        MovieDetails movie = movieService.get(id);
        MovieDetailsDto movieDto = null;
        if(movie != null){
            File file = new File("src/main/resources/static/images/uploads/" + movie.getImage());
            FileInputStream input;
            try{
                input = new FileInputStream(file);
                MultipartFile multiImg = new MockMultipartFile("file", file.getName(), "text/plain",
                                                                IOUtils.toByteArray(input));
                movieDto = new MovieDetailsDto(movie.getMovieid(), movie.getMoviename(), movie.getDirector(), movie.getDuration(),
                            movie.getGenre(), movie.getDescription(), movie.getRating(), multiImg);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            model.addAttribute("movieDto", movieDto);
        }else{
            model.addAttribute("movieDto", new MovieDetailsDto());
        }
        model.addAttribute("action", "/saveMovie");
        return "admin/updateMovie";
    }

    @GetMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable(name = "id") String id){
        movieService.delete(id);
        return "redirect:/viewMovies";
    }

}

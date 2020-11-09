package com.projects4.mcinema.controller;

import com.projects4.mcinema.model.MovieDetails;
import com.projects4.mcinema.service.CustomerService;
import com.projects4.mcinema.service.MovieDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private CustomerService service;

    @Autowired
    private MovieDetailsService movieService;

    @GetMapping("/")
    public String home(Model model){
        List<MovieDetails> movieList = movieService.listAll();
        model.addAttribute("movieList", movieList);
        return "main/index";
    }

    @GetMapping("/movieMain/{id}")
    public ModelAndView movieMainDetails(@PathVariable(name = "id") String id){
        ModelAndView mav = new ModelAndView("main/movieDetails");
        Optional<MovieDetails> movieOptional = movieService.findById(id);
        MovieDetails movie = movieOptional.get();
        mav.addObject("movie", movie);
        return mav;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "main/loginForm";
    }

    @GetMapping("/error")
    public String error(){
        return "main/404";
    }

    @PostMapping("/loginSuccess")
    public String success(){
        return "main/loginSuccess";
    }

    @GetMapping("/logout")
    public String logout(){
        return "main/index";
    }

    @PostMapping("/login")
    public String doLogin(Model model){
        return  "/index";
    }

    @GetMapping("/test")
    public String test(Model model){
        return "main/main_layout";
    }

    @GetMapping("/movieMain/bookTicket")
    public String bookTicket(){
        return "main/bookTicket";
    }


}

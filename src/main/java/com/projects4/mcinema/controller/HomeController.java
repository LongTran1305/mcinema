package com.projects4.mcinema.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String home(Model model){
        return "main/index";
    }
//    @PostMapping("/login")
//    public String doLogin(){
//        return  "main/index";
//    }
    @GetMapping("/showLogin")
    public String loginForm(Model model){
        return "main/loginForm";
    }
    @GetMapping("/error")
    public String error(Model model){
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

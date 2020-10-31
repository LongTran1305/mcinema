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
    @PostMapping("/login")
    public String doLogin(Model model){
        return  "/index";
    }
    @GetMapping("/admin")
    public String admin(Model model){
        return "admin/index";
    }
}

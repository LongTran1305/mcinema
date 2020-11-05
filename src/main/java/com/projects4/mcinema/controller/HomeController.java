package com.projects4.mcinema.controller;

import com.projects4.mcinema.model.Customer;
import com.projects4.mcinema.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class HomeController {
    @Autowired
    private CustomerService service;
    @GetMapping("/")
    public String home(Model model){

        return "main/index";
    }
    @GetMapping("/login")
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

    @PostMapping("/login")
    public String doLogin(Model model){
        return  "/index";
    }
    @GetMapping("/admin")
    public String admin(Model model){
        return "admin/index";
    }

}

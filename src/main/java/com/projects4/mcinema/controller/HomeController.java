package com.projects4.mcinema.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String home(Model model){
        return "main/index";
    }
//    @GetMapping("/login")
//    public String doLogin(){
//        return  "main/index";
//    }
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
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
        //return "redirect:/login?logout=true";
        //return "main/index";
    }
    @PostMapping("/login")
    public String doLogin(Model model){
        return  "/index";
    }

    @GetMapping("/aboutus")
    public String aboutUs(Model model){
        return "main/aboutUs";
    }

}

package com.projects4.mcinema.controller;

import com.projects4.mcinema.model.FAQs;
import com.projects4.mcinema.service.FAQsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private FAQsService faqsService;

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

}

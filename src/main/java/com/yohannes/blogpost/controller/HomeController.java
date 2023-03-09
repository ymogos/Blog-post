package com.yohannes.blogpost.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Yohannes
 */
@Controller
public class HomeController {
   
 
    @GetMapping("/")
    public String displayIndex(Model model) {
        return "index";
    }
    
}

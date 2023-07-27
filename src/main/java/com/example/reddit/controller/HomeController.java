package com.example.reddit.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(Model m, HttpServletRequest r){
        m.addAttribute("mssg", "Hello World");
        return "Hello, Springit!";
    }
}

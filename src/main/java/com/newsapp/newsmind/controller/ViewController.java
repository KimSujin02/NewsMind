package com.newsapp.newsmind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
//    @RequestMapping("/")
//    public String root() {
//        return "redirect:/login";
//    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login"; // templates/login.html
    }

    @GetMapping("/settings/{id}")
    public String settings(@PathVariable("id") String id) {
        return "settings/" + id;
    }

    @GetMapping("/news/feed")
    public String feed() {
        return "news/feed";
    }

    @GetMapping("/news/detail")
    public String detail() {
        return "news/detail";
    }
}
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

//    @RequestMapping("/page/{folder}/{id}")
//    public String pageMove(@PathVariable String folder, @PathVariable String id){
//        return folder + "/" + id;
//    }

    @GetMapping("/settings/initial")
    public String initial() {
        return "settings/initial";
    }

    @GetMapping("/settings/country")
    public String country() {
        return "settings/country";
    }

    @GetMapping("/settings/category")
    public String category() {
        return "settings/category";
    }

    @GetMapping("/settings/keyword")
    public String keyword() {
        return "settings/keyword";
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
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.controllers;

import com.nhs.dto.PostDto;
import com.nhs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author admin
 */
@Controller
public class IndexController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/posts/")
    public String list(Model model) {
        model.addAttribute("post", new PostDto());
        return "posts";
    }

    @PostMapping("/posts/")
    public String add(@ModelAttribute(value = "post") PostDto p) {
        System.out.println(p.getImgFile());
        if (postService.addPost(p) ==null) {
            return "/";
        }
        return "posts";
    }
}

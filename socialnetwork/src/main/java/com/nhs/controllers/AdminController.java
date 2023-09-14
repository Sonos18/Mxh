/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.controllers;

import com.nhs.pojo.Users;
import com.nhs.service.AdminService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ADMIN
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService AdminService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/stats")
    public String stats(Model model, @RequestParam Map<String, String> params) {

        model.addAttribute("stat", AdminService.stat(params));
        System.out.println(AdminService.stat(params));
        return "admin";
    }

    @GetMapping("/statsLike")
    public String statsLike(Model model, @RequestParam Map<String, String> params) {

        model.addAttribute("like", AdminService.statLike(params));
        return "StatLike";
    }

    @GetMapping("/statsPost")
    public String statsPost(Model model, @RequestParam Map<String, String> params) {

        model.addAttribute("post", AdminService.statPost(params));
        return "StatPost";
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.controllers;

import com.nhs.dto.PostDto;
import com.nhs.pojo.Hashtags;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import com.nhs.service.PostService;
import com.nhs.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @GetMapping("/posts/")
    @CrossOrigin
    public ResponseEntity<List<PostDto>> list() {
        return new ResponseEntity<>(this.postService.getPosts(), HttpStatus.OK);
    }
    
    @PostMapping("/posts/add/")
    @CrossOrigin
    public ResponseEntity<?> add(@RequestBody PostDto postDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            return new ResponseEntity<>(this.postService.addPost(postDto, currentUser), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
///////
    @GetMapping("/posts/{id}")
    @CrossOrigin
    public ResponseEntity<PostDto> post(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    @CrossOrigin
    public ResponseEntity<?> update(@RequestBody PostDto postDto, @PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            return new ResponseEntity<>(this.postService.deletePost(id,currentUser), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    
}

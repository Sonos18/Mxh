/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.controllers;

import com.nhs.dto.PostDto;
import com.nhs.pojo.Posts;
import com.nhs.service.PostService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/posts/")
    @CrossOrigin
    public ResponseEntity<List<PostDto>> list() {
        return new ResponseEntity<>(this.postService.getPosts(), HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    @CrossOrigin
    public ResponseEntity<?> update(@RequestBody PostDto postDto, @PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.postService.updatePost(postDto,id),HttpStatus.OK );
    }

    @DeleteMapping("/posts/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.postService.deletePost(id);
    }
    
    @PostMapping("/posts/add/")
    @CrossOrigin
    public ResponseEntity<Posts> add(@RequestParam PostDto postDto) {
        return new ResponseEntity<>(this.postService.addPost(postDto), HttpStatus.CREATED);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhs.dto.PostDto;
import com.nhs.pojo.Hashtags;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import com.nhs.service.LikeService;
import com.nhs.service.PostService;
import com.nhs.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.ejb.PostActivate;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private LikeService likeService;

    @GetMapping("/posts/")
    @CrossOrigin
    public ResponseEntity<List<PostDto>> list() {
        return new ResponseEntity<>(this.postService.getPosts(), HttpStatus.OK);
    }

    @PostMapping(path = "/posts/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<?> add(@RequestParam("content") String content, @RequestParam("hashtags") String hashtags, @RequestParam("imgFile") MultipartFile imgFile) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            ObjectMapper mapper = new ObjectMapper();
            List<String> hashtagList = Arrays.asList(mapper.readValue(hashtags, String[].class));
            PostDto postDto = PostDto.builder()
                    .content(content)
                    .hashtags(hashtagList)
                    .build();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            return new ResponseEntity<>(this.postService.addPost(postDto, currentUser), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/posts/{id}/")
    @CrossOrigin
    public ResponseEntity<?> update(@RequestBody PostDto postDto, @PathVariable(value = "id") int id) {
        postDto.setId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            Posts post = this.postService.updatePost(postDto, currentUser.getUserId());
            if (post != null) {
                return new ResponseEntity<>(post, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("You do not have permission to update post", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/posts/{id}/")
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            if (this.postService.deletePost(id, currentUser)) {
                return new ResponseEntity<>("Post is delete", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>("You do not have permission to delete post", HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/posts/{id}/")
    @CrossOrigin
    public ResponseEntity<?> isLocked(@PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            if (this.postService.isLocked(id, currentUser.getUserId())) {
                return new ResponseEntity<>("Successfull", HttpStatus.OK);
            }
            return new ResponseEntity<>("You do not have permission to delete post", HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
// Like Post

    @PostMapping("/posts/{id}/like/")
    @CrossOrigin
    public ResponseEntity<?> like(@PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            if (this.likeService.like(id, currentUser)) {
                return new ResponseEntity<>("Successfull", HttpStatus.OK);
            }
            return new ResponseEntity<>("Failed", HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    ////
    @GetMapping("/posts/{id}/")
    @CrossOrigin
    public ResponseEntity<PostDto> post(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(this.postService.getPostById(id), HttpStatus.OK);
    }

}

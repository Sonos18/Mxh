/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.controllers;

import com.nhs.dto.CommentDto;
import com.nhs.pojo.Comments;
import com.nhs.pojo.Users;
import com.nhs.service.CommentService;
import com.nhs.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/api/posts/{postID}/comments/")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable("postID") Integer postID) {
        return ResponseEntity.ok(this.commentService.getAllCommentsForPost(postID));
    }

    @PostMapping("/api/posts/{postID}/comments/")
    public ResponseEntity<?> createComment(@RequestParam Map<String, String> params ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            CommentDto commentDto=CommentDto.builder()
                    .content(params.get("content"))
                    .postId(Integer.parseInt(params.get("postId")))
                    .build();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            CommentDto com = this.commentService.createComment(commentDto, commentDto.getPostId(), currentUser);
            if (com == null) {
                return new ResponseEntity<>("You do not have permission to action", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(com, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("api/posts/{postID}/comments/{id}/")
    public ResponseEntity<?> deleteComment(@PathVariable("postID") Integer postID, @PathVariable("id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            if (!this.commentService.deleteComment(id, postID, currentUser)) {
                return new ResponseEntity<>("You do not have permission to action", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("api/posts/{postID}/comments/{id}/")
    public ResponseEntity<?> updateComment(@RequestBody CommentDto commentDto, @PathVariable("postID") Integer postID) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            if (!this.commentService.updateComment(commentDto, postID, currentUser)) {
                return new ResponseEntity<>("You do not have permission to action", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("api/posts/{postID}/comments/{id}/")
    public ResponseEntity<?> replyComment(@RequestBody CommentDto commentDto, @PathVariable("postID") Integer postID, @PathVariable("id") Integer parentCommentID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            if (!this.commentService.replyComment(commentDto, parentCommentID, postID, currentUser)) {
                return new ResponseEntity<>("You do not have permission to action", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

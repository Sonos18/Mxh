/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.nhs.dto.CommentDto;
import com.nhs.dto.PostDto;
import com.nhs.pojo.Comments;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import com.nhs.repository.CommentRepository;
import com.nhs.repository.PostRepository;
import com.nhs.service.CommentService;
import com.nhs.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<CommentDto> getAllCommentsForPost(Integer postID) {
        Posts post = this.postRepository.getPostById(postID);
        if (post == null) {
            return null;
        }
        List<Comments> comments = this.commentRepository.getCommentsForPost(postID);
        List<CommentDto> commentDtos = new ArrayList<>();
        comments.forEach(c -> {
            CommentDto commentDto = CommentDto.builder()
                    .commentId(c.getCommentId())
                    .createAt(c.getCreateAt())
                    .content(c.getContent())
                    .user(c.getUserId().getUsername())
                    .build();
            commentDtos.add(commentDto);
        });
        return commentDtos;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, int postID, Users user) {
        Posts post = this.postRepository.getPostById(postID);
        if (post == null) {
            return null;
        }
        Comments comment = new Comments(commentDto.getContent(), user, post);
        if (this.commentRepository.createComment(comment)) {
            return commentDto;
        }
        return null;

    }

    @Override
    public boolean deleteComment(int commentID, int postID) {
        return false;
    }

    @Override
    public boolean updateComment(CommentDto commentDto, int postID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CommentDto replyComment(PostDto postDto, int commentID, int postID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

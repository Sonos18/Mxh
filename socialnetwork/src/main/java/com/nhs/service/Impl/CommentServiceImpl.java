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
    public boolean deleteComment(int commentID, int postID, Users user) {
        Comments comment = this.commentRepository.getCommentByID(commentID);
        Posts posts = this.postRepository.getPostById(postID);
        if (comment == null || posts == null) {
            return false;
        }
        return this.commentRepository.deleteComment(commentID, posts, user);
    }

    @Override
    public boolean updateComment(CommentDto commentDto, int postID,Users user) {
        Posts post=this.postRepository.getPostById(postID);
        Comments c=this.commentRepository.checkComment(commentDto.getCommentId(), post, user);
        if (c==null)
            return false;
        c.setContent(commentDto.getContent());
        return this.commentRepository.updateComment(c);
    }

    @Override
    public boolean replyComment(CommentDto commentDto, int commentID, int postID,Users user) {
        Posts post=this.postRepository.getPostById(postID);
        Comments com=this.commentRepository.getCommentByID(commentID);
        if(post==null||com==null)
            return false;
        Comments comment=new Comments();
        comment.setContent(commentDto.getContent());
        comment.setPostId(post);
        comment.setParentCommentId(com);
        comment.setUserId(user);
        return this.commentRepository.createComment(comment);
        
    }

}

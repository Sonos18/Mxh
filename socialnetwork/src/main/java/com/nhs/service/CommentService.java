/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.service;

import com.nhs.dto.CommentDto;
import com.nhs.dto.PostDto;
import com.nhs.pojo.Users;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface CommentService {
    List<CommentDto> getAllCommentsForPost(Integer postID);
    CommentDto createComment(CommentDto comentDto,int postID,Users user);
    boolean deleteComment(int commentID,int postID);
    boolean updateComment(CommentDto commentDto,int postID);
    CommentDto replyComment(PostDto postDto,int commentID,int postID);
}

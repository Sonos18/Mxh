/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.service;

import com.nhs.dto.PostDto;
import com.nhs.pojo.Hashtags;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import java.util.List;

/**
 *
 * @author admin
 */
public interface PostService {

    List<PostDto> getPosts();

    Posts addPost(PostDto p,Users user);

    Posts updatePost(PostDto p,int id);

    PostDto getPostById(int id);

    boolean deletePost(int id, Users user);
    
    boolean isLocked(int postID,int userID);

    List<PostDto> getPostsForUser(Users user);
    
    PostDto toPostDto(Posts p);
}

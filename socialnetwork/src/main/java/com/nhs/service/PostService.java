/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.service;

import com.nhs.dto.PostDto;
import com.nhs.pojo.Posts;
import java.util.List;

/**
 *
 * @author admin
 */
public interface PostService {

    List<PostDto> getPosts();

    Posts addPost(PostDto p);

    boolean updatePost(PostDto p,int id);

    PostDto getPostById(int id);

    boolean deletePost(int id);

}

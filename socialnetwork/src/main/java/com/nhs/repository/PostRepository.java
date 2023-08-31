/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.repository;

import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import java.util.List;

/**
 *
 * @author admin
 */
public interface PostRepository {

    List<Posts> getPosts();

    Posts addPost(Posts p);

    Posts updatePost(Posts p);

    Posts getPostById(int id);

    boolean deletePost(Posts p);

    List<String> getHashtagTextsForPost(int postId);

    List<Posts> getPostsForUser(Users user);

}

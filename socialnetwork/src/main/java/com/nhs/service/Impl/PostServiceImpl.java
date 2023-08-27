/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhs.dto.PostDto;
import com.nhs.pojo.Hashtags;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import com.nhs.repository.HashtagRepository;
import com.nhs.repository.LikeRepository;
import com.nhs.repository.PostRepository;
import com.nhs.service.PostService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Override
    public boolean updatePost(PostDto postDto, int id) {
        Posts post = this.postRepository.getPostById(id);
        if (post == null) {
            return false;
        }
        post.setContent(postDto.getContent());
        return this.postRepository.updatePost(post);
    }

    //Done
    @Override
    public boolean deletePost(int id, Users user) {
//        Posts post = this.postRepository.getPostById(id);
//        if (post == null) {
//            return false;
//        }
//        if(post.getUserId().getUserId() == user.getUserId())
//        {
//            return this.postRepository.deletePost(id);
//        }
        return false;
    }

    @Override
    public Posts addPost(PostDto postDto, Users user) {
        if (postDto.getImgFile() != null && !postDto.getImgFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(postDto.getImgFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                postDto.setFile(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Posts p = new Posts(postDto.getContent(), postDto.getFile());
        if (postDto.getHashtags().size() > 0) {
            Set<Hashtags> hashtagses = new HashSet<>();
            for (String hString : postDto.getHashtags()) {
                if (hashtagRepository.getHashtagByText(hString) == null) {
                    hashtagRepository.addHashtag(hString);
                }
                hashtagses.add(hashtagRepository.getHashtagByText(hString));
            }
            p.setHashtagsSet(hashtagses);
        }
        p.setUserId(user);
        return postRepository.addPost(p);

    }

    @Override
    public List<PostDto> getPosts() {
        List<Posts> posts = this.postRepository.getPosts();
        List<PostDto> postDtos = new ArrayList<>();
        posts.forEach(p -> {
            PostDto postDto = PostDto.builder()
                    .id(p.getPostId())
                    .isLocked(p.getIsLocked())
                    .content(p.getContent())
                    .file(p.getImage())
                    .userId(p.getUserId().getUserId())
                    .createAt(p.getCreatedAt())
                    .hashtags(this.postRepository.getHashtagTextsForPost(p.getPostId()))
                    .like(this.likeRepository.likeofPost(p.getPostId()))
                    .build();
            postDtos.add(postDto);
        });
        return postDtos;
    }

    @Override
    public PostDto getPostById(int id) {
        Posts p = this.postRepository.getPostById(id);
        PostDto postDto = PostDto.builder()
                .id(p.getPostId())
                .isLocked(p.getIsLocked())
                .content(p.getContent())
                .userId(p.getUserId().getUserId())
                .createAt(p.getCreatedAt())
                .hashtags(this.postRepository.getHashtagTextsForPost(id))
                .build();
        return postDto;
    }

    @Override
    public Hashtags addH(String h) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PostDto> getPostsForUser(Users user) {
        List<Posts> posts = this.postRepository.getPostsForUser(user);
        List<PostDto> postDtos = new ArrayList<>();
        posts.forEach(p -> {
            PostDto postDto = PostDto.builder()
                    .id(p.getPostId())
                    .isLocked(p.getIsLocked())
                    .content(p.getContent())
                    .file(p.getImage())
                    .userId(p.getUserId().getUserId())
                    .createAt(p.getCreatedAt())
                    .hashtags(this.postRepository.getHashtagTextsForPost(p.getPostId()))
                    .like(this.likeRepository.likeofPost(p.getPostId()))
                    .build();
            postDtos.add(postDto);
        });
        return postDtos;
    }

}

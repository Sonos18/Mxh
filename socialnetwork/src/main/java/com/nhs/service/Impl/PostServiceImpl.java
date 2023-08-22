/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.cloudinary.Cloudinary;
import com.nhs.dto.PostDto;
import com.nhs.repository.PostRepository;
import com.nhs.service.PostService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudinary.utils.ObjectUtils;
import com.nhs.pojo.Hashtags;
import com.nhs.pojo.Posts;
import com.nhs.repository.HashtagRepository;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private Cloudinary cloudinary;

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
    public boolean deletePost(int id) {
        return this.postRepository.deletePost(id);
    }

    @Override
    @Transactional
    public Posts addPost(PostDto postDto) {
        System.out.println(postDto.getImgFile());
        if (!postDto.getImgFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(postDto.getImgFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                postDto.setFile(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Posts p = new Posts( postDto.getContent(),postDto.getFile());
        if (postDto.getHashTags().size() > 0) {
            Hashtags h = new Hashtags("");
            for (String hString : postDto.getHashTags()) {
                h = hashtagRepository.getHashtagByText(hString);
                if (h == null) {
                    hashtagRepository.addHashtag(hString);
                    h = hashtagRepository.getHashtagByText(hString);
                    p.getHashtagsSet().add(h);
                }
            }
        }
        
        
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
                .build();
        return postDto;
    }

}

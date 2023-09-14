/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhs.dto.PostDto;
import com.nhs.dto.UsersDto;
import com.nhs.pojo.Hashtags;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import com.nhs.repository.HashtagRepository;
import com.nhs.repository.LikeRepository;
import com.nhs.repository.PostRepository;
import com.nhs.service.PostService;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public Posts updatePost(PostDto postDto, int id) {
         if (postDto.getImgFile() != null) {
            try {
                Map res = this.cloudinary.uploader().upload(postDto.getImgFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                postDto.setFile(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else postDto.setFile("");
         Posts post = this.postRepository.getPostById(postDto.getId());
        if (post == null || post.getUserId().getUserId() != id) {
            return null;
        }
        if (postDto.getHashtags().size() > 0) {
            Set<Hashtags> hashtagses = new HashSet<>();
            for (String hString : postDto.getHashtags()) {
                if (hashtagRepository.getHashtagByText(hString) == null) {
                    hashtagRepository.addHashtag(hString);
                }
                hashtagses.add(hashtagRepository.getHashtagByText(hString));
            }
            post.setHashtagsSet(hashtagses);
        }
        post.setContent(postDto.getContent());
        post.setImage(postDto.getFile());
        post.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.postRepository.updatePost(post);
    }

    //Done
    @Override
    public boolean deletePost(int id, Users user) {
        Posts post = this.postRepository.getPostById(id);
        if (post == null || post.getUserId().getUserId()!=user.getUserId()) {
            return false;
        }
        return this.postRepository.deletePost(post);
    }

    @Override
    public Posts addPost(PostDto postDto, Users user) {
        if (postDto.getImgFile() != null) {
            try {
                Map res = this.cloudinary.uploader().upload(postDto.getImgFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                postDto.setFile(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else postDto.setFile(null);

        Posts p = new Posts();
        p.setIsAuction(postDto.getIsAuction());
        p.setContent(postDto.getContent());
        p.setImage(postDto.getFile());
        p.setIsLocked(Boolean.FALSE);
        p.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
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
            postDtos.add(this.toPostDto(p));
        });
        return postDtos;
    }

    @Override
    public PostDto getPostById(int id) {
        Posts p = this.postRepository.getPostById(id);
        return this.toPostDto(p);
    }

    @Override
    public List<PostDto> getPostsForUser(Users user) {
        List<Posts> posts = this.postRepository.getPostsForUser(user);
        List<PostDto> postDtos = new ArrayList<>();
        posts.forEach(p -> {
            postDtos.add(this.toPostDto(p));
        });
        return postDtos;

    }

    @Override
    public PostDto toPostDto(Posts p) {
        UsersDto userDto = UsersDto.builder()
                .userId(p.getUserId().getUserId())
                .username(p.getUserId().getUsername())
                .avatar(p.getUserId().getAvatar())
                .build();
        PostDto postDto = PostDto.builder()
                .id(p.getPostId())
                .content(p.getContent())
                .file(p.getImage())
                .usersDto(userDto)
                .createAt(p.getCreatedAt())
                .hashtags(this.postRepository.getHashtagTextsForPost(p.getPostId()))
                .build();
        postDto.setUsernameLike(this.likeRepository.likeofPost(p.getPostId()));
        return postDto;
    }

    @Override
    public boolean isLocked(int postID,int userID) {
        Posts post=this.postRepository.getPostById(postID);
        if(post==null||post.getUserId().getUserId()!=userID)
            return false;
        post.setIsLocked(!post.getIsLocked());
        return (this.postRepository.updatePost(post)!=null?true:false);
    }

}

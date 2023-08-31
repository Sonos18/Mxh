/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.nhs.pojo.Likes;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import com.nhs.repository.LikeRepository;
import com.nhs.repository.PostRepository;
import com.nhs.service.LikeService;
import com.nhs.service.NotificationService;
import java.io.NotSerializableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository poRe;
    @Autowired
    private NotificationService notificationService;

    @Override
    public boolean like(int postID,Users user) {
        Posts post=this.poRe.getPostById(postID);
        Likes like=new Likes();
        like.setPostId(post);
        like.setUserId(user);
        return(this.notificationService.createNotification(post,"like",user)
            && this.likeRepository.like(like));
    }

    @Override
    public boolean disLike(int postID) {
        return this.likeRepository.disLike(postID);
    }

}

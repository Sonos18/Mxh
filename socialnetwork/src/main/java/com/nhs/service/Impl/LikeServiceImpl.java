/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.nhs.pojo.Likes;
import com.nhs.repository.LikeRepository;
import com.nhs.service.LikeService;
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

    @Override
    public boolean like(Likes l) {
        return this.likeRepository.like(l);
    }

    @Override
    public boolean disLike(int postID) {
        return this.likeRepository.disLike(postID);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.service;

import com.nhs.pojo.Likes;

/**
 *
 * @author DELL
 */
public interface LikeService {

    boolean like(Likes l);

    boolean disLike(int postID);
}

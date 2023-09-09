/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.service;

import com.nhs.pojo.Likes;
import com.nhs.pojo.Users;

/**
 *
 * @author DELL
 */
public interface LikeService {

    boolean like(int postID,Users user);

    boolean disLike(int postID, Users user);
}

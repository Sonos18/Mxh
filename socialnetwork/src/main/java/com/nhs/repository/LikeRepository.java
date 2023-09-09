/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.repository;

import com.nhs.pojo.Likes;
import com.nhs.pojo.Users;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface LikeRepository {

    List<String> likeofPost(int postID);

    boolean like(Likes l);

    boolean disLike(int postID, Users user);
}

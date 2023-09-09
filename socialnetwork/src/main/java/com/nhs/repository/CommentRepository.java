/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.repository;

import com.nhs.pojo.Comments;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface CommentRepository {
    List<Comments> getCommentsForPost(int postID);
    Comments createComment(Comments comment);
    boolean deleteComment(int comemntID,Posts post, Users user);
    boolean updateComment(Comments comment);
    Comments getCommentByID(int id);
    Comments checkComment(int comemntID, Posts post, Users user);
}

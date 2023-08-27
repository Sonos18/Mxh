/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.repository;

import com.nhs.pojo.Comments;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface CommentRepository {
    List<Comments> getCommentsForPost(int postID);
    boolean createComment(Comments comment);
    boolean deleteComment(Comments comemnt);
    boolean updateComment(Comments comment);
}

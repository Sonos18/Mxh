/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository.Impl;

import com.nhs.pojo.Comments;
import com.nhs.pojo.Posts;
import com.nhs.repository.CommentRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Transactional
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Comments> getCommentsForPost(int postID) {
        Session s = this.factory.getObject().getCurrentSession();
        Posts post = s.get(Posts.class, postID);
        Query q = s.createQuery("FROM Comments c WHERE c.postId = :post");
        q.setParameter("post", post);
        return q.getResultList();
    }

    @Override
    public boolean createComment(Comments comment) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(comment);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteComment(Comments comment) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(comment);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateComment(Comments comment) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(comment);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }


}

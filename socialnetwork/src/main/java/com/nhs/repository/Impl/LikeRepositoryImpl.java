/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository.Impl;

import com.nhs.pojo.Likes;
import com.nhs.pojo.Posts;
import com.nhs.repository.LikeRepository;
import com.nhs.repository.PostRepository;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Transactional
@Repository
public class LikeRepositoryImpl implements LikeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired 
    private PostRepository postRepository;

    @Override
    public Long likeofPost(int postID) {
        Session s = this.factory.getObject().getCurrentSession();
        Posts post = s.get(Posts.class, postID);
        Query q = s.createQuery("SELECT COUNT(l) FROM Likes l WHERE l.postId = :post");
        q.setParameter("post", post);
        return (Long) q.getSingleResult(); // Kết quả là một số nguyên
    }

    @Override
    public boolean like(Likes l) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(l);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean disLike(int postID) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("DELETE FROM Like l WHERE l.post.id = :postID");
        q.setParameter("postID", this.postRepository.getPostById(postID));
        int deletedCount = q.executeUpdate();
        boolean deleted = deletedCount > 0; 
        return deleted;

    }

}

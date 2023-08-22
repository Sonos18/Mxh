/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository.Impl;

import com.nhs.pojo.Posts;
import com.nhs.repository.PostRepository;
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
 * @author admin
 */
@Repository
@Transactional
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Posts> getPosts() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Posts");
        return q.getResultList();
    }

   

    @Override
    public Posts getPostById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Posts.class, id);
    }

    @Override
    public boolean deletePost(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {   
            Posts p = this.getPostById(id);
            s.delete(p);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Posts addPost(Posts post) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(post);
            return post;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updatePost(Posts post) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(post);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

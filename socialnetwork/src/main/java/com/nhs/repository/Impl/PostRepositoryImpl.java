/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository.Impl;

import com.nhs.pojo.Hashtags;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import com.nhs.repository.PostRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Posts> getPosts() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Posts");
        return q.getResultList();

    }

    public List<String> getHashtagTextsForPost(int postId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<String> query = builder.createQuery(String.class);

        Root<Posts> postRoot = query.from(Posts.class);
        Join<Posts, Hashtags> hashtagsJoin = postRoot.join("hashtagsSet");
        query.select(hashtagsJoin.get("hashtagText")).distinct(true);
        query.where(builder.equal(postRoot.get("postId"), postId));

        List<String> hashtags = session.createQuery(query).getResultList();

        return hashtags;
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

    @Override
    public List<Posts> getPostsForUser(Users user) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Posts Where userId=:us");
        q.setParameter("us", user);
        return q.getResultList();
    }

}

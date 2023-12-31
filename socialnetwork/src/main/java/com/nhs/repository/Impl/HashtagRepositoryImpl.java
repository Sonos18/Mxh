/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository.Impl;

import com.nhs.pojo.Hashtags;
import com.nhs.repository.HashtagRepository;
import javax.persistence.NoResultException;
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
public class HashtagRepositoryImpl implements HashtagRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Hashtags getHashtagByText(String text) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Hashtags Where hashtagText=:text");
        q.setParameter("text", text);
        try {
            return (Hashtags) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean addHashtag(String h) {
        Hashtags hash = new Hashtags(h);
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(hash);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkHastag(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Hashtags getHashtagByID(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Hashtags Where hashtag_id=:id");
        q.setParameter("id", id);
        return (Hashtags) q.getSingleResult();
    }

}

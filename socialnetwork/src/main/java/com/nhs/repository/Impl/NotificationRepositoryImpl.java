/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository.Impl;

import com.nhs.pojo.Notifications;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import com.nhs.repository.NotificationRepository;
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
@Repository
@Transactional
public class NotificationRepositoryImpl implements NotificationRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Notifications createNotification(Notifications not) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Notifications Where targetId=:p");
        s.save(not);
        return not;
    }

    @Override
    public List<Notifications> getNotificationForUser(Posts p) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Notifications Where targetId=:p");
        q.setParameter("p", p);
        return q.getResultList();
    }

    @Override
    public Notifications getNotificationById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Notifications.class, id);
    }

    @Override
    public Notifications update(Notifications not) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(not);
            return not;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkNotification(Posts post, Users user, String action) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Notifications Where actionType=:ac and targetId=:po and userId=:us");
        q.setParameter("ac", action);
        q.setParameter("po", post);
        q.setParameter("us", user);
        List<Notifications> notifications = q.getResultList();
        return !notifications.isEmpty();
    }
}

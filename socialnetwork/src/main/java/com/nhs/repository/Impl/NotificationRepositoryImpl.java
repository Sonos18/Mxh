/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository.Impl;

import com.nhs.pojo.Notifications;
import com.nhs.pojo.Users;
import com.nhs.repository.NotificationRepository;
import java.util.List;
import javax.persistence.Query;
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
        s.save(not);
        return not;
    }

    @Override
    public List<Notifications> getNotificationForUser(Users user) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Notifications Where userId=:us");
        q.setParameter("us", user);
        return q.getResultList();
    }
}

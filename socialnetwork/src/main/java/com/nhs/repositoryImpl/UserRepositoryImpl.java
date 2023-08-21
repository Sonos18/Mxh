/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repositoryImpl;

import com.nhs.pojo.Users;
import com.nhs.repository.UserRepository;
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
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Users getUserByID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Users.class, id);
    }

    @Override
    public Users getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Users Where username=:un");
        q.setParameter("un", username);
        return (Users) q.getSingleResult();
    }

    @Override
    public Users addUser(Users user) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
                s.save(user);
            return user;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

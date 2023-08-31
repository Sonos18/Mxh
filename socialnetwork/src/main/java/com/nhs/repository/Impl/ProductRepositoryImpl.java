/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository.Impl;

import com.nhs.pojo.Products;
import com.nhs.repository.ProductRepository;
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
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Products getProductByID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Products.class, id);
    }

    @Override
    public Products createProduct(Products pro) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(pro);
            return pro;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

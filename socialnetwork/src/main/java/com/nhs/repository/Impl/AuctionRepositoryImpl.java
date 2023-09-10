/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository.Impl;

import com.nhs.pojo.Auction;
import com.nhs.repository.AuctionRepository;
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
public class AuctionRepositoryImpl implements AuctionRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Auction> getAllAuctions() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT a From Auction a JOIN a.postId p Where a.winnerUserId IS NULL ORDER BY p.createdAt DESC");
        return q.getResultList();
    }

    @Override
    public Auction createAuction(Auction au) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(au);
            return au;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAuction(Auction au) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(au);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Auction updateAuction(Auction au) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(au);
            return au;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Auction getauAuctionByID(int auctionID) {
        Session s=this.factory.getObject().getCurrentSession();
        return s.get(Auction.class, auctionID);
    }

}

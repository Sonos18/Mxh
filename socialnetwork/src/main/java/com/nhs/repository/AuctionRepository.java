/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.repository;

import com.nhs.pojo.Auction;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface AuctionRepository {
    List<Auction> getAllAuctions();
    Auction createAuction(Auction au);
    boolean deleteAuction(Auction au);
    Auction updateAuction(Auction au);
    Auction getauAuctionByID(int auctionID);
}

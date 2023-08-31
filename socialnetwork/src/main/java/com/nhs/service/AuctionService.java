/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.service;

import com.nhs.dto.AuctionDto;
import com.nhs.pojo.Auction;
import com.nhs.pojo.Users;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface AuctionService {

    List<AuctionDto> getAllAuctions();

    Auction createAuction(AuctionDto au,Users user);

    boolean deleteAuction(AuctionDto au);

    boolean updateAuction(AuctionDto au,int userID);
}

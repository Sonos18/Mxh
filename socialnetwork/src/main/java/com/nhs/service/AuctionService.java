/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhs.dto.AuctionDto;
import com.nhs.pojo.Auction;
import com.nhs.pojo.Users;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author DELL
 */
public interface AuctionService {

    List<AuctionDto> getAllAuctions();

    Auction createAuction(Map<String, String> params,Users user, MultipartFile imgFile)throws JsonProcessingException;

    boolean deleteAuction(AuctionDto au);

    boolean updateAuction(AuctionDto au,int userID);
    
    AuctionDto toAuctionDto(Auction auction);
    
    AuctionDto winningBid(Map<String, String> params);
    
}

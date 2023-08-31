/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.nhs.dto.AuctionDto;
import com.nhs.dto.PostDto;
import com.nhs.dto.ProductsDto;
import com.nhs.dto.UsersDto;
import com.nhs.pojo.Auction;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Products;
import com.nhs.pojo.Users;
import com.nhs.repository.AuctionRepository;
import com.nhs.service.AuctionService;
import com.nhs.service.PostService;
import com.nhs.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private ProductService productService;

    @Override
    public List<AuctionDto> getAllAuctions() {
        List<Auction> auctions = this.auctionRepository.getAllAuctions();
        List<AuctionDto> auctionDtos = new ArrayList<>();
        auctions.forEach(auction -> {
            UsersDto userDto = UsersDto.builder()
                    .userId(auction.getWinnerUserId().getUserId())
                    .email(auction.getWinnerUserId().getEmail())
                    .username(auction.getWinnerUserId().getUsername())
                    .build();
            PostDto postDto = this.postService.getPostById(auction.getPostId().getPostId());
            ProductsDto productDto = this.productService.getProductByID(auction.getProductId().getId());
            AuctionDto auctionDto = AuctionDto.builder()
                    .auctionId(auction.getAuctionId())
                    .startTime(auction.getStartTime())
                    .endTime(auction.getEndTime())
                    .startingPrice(auction.getStartingPrice())
                    .buyoutPrice(auction.getBuyoutPrice())
                    .winningBid(auction.getWinningBid())
                    .postDto(postDto)
                    .productsDto(productDto)
                    .winnerUser(userDto)
                    .build();
            auctionDtos.add(auctionDto);
        });
        return auctionDtos;
    }

    @Override
    public Auction createAuction(AuctionDto au, Users user) {
        System.out.println(au.getPostDto().getContent());
        Auction auction = new Auction();
        PostDto postDto=au.getPostDto();
        Posts post=this.postService.addPost(postDto, user);
        Products pro=this.productService.createProduct(au.getProductsDto());
        auction.setStartTime(au.getStartTime());
        auction.setEndTime(au.getEndTime());
        auction.setBuyoutPrice(au.getBuyoutPrice());
        auction.setStartingPrice(au.getStartingPrice());
        auction.setWinningBid(au.getWinningBid());
        auction.setPostId(post);
        auction.setProductId(pro);
        return this.auctionRepository.createAuction(auction);                             
    }

    @Override
    public boolean deleteAuction(AuctionDto au) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateAuction(AuctionDto au, int userID) {
        Auction auction=this.auctionRepository.getauAuctionByID(au.getAuctionId());
        if(auction==null)
            return false;
        au.getPostDto().setId(auction.getPostId().getPostId());
        Posts post= this.postService.updatePost(au.getPostDto(), userID);
        if(post==null)
            return false;
        auction.setPostId(post);
        auction.setBuyoutPrice(au.getBuyoutPrice());
        auction.setStartTime(au.getStartTime());
        auction.setEndTime(au.getEndTime());
        auction.setStartingPrice(au.getStartingPrice());
        auction.setWinningBid(au.getWinningBid());
        return (this.auctionRepository.updateAuction(auction)==null?false:true);
    }


    
}

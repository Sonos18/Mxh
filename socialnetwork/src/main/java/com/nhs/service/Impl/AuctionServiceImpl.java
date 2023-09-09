/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.nhs.service.UserService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private UserService userService;

    @Override
    public List<AuctionDto> getAllAuctions() {
        List<Auction> auctions = this.auctionRepository.getAllAuctions();
        List<AuctionDto> auctionDtos = new ArrayList<>();
        UsersDto userDto = new UsersDto();
        auctions.forEach(auction -> {
            AuctionDto auctionDto = this.toAuctionDto(auction);
            if (auction.getWinnerUserId() != null) {
                auctionDto.setWinnerUser(this.userService.toUsersDto(auction.getWinnerUserId()));
            }
            PostDto postDto = this.postService.getPostById(auction.getPostId().getPostId());
            ProductsDto productDto = this.productService.getProductByID(auction.getProductId().getId());
            auctionDto.setProductsDto(productDto);
            auctionDto.setPostDto(postDto);
            auctionDtos.add(auctionDto);
        });
        return auctionDtos;
    }

    /**
     *
     * @param params
     * @param user
     * @param imgFile
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public Auction createAuction(Map<String, String> params, Users user, MultipartFile imgFile) throws JsonProcessingException {
        Auction auction = new Auction();
        ObjectMapper mapper = new ObjectMapper();
        List<String> hashtagList = new ArrayList<>();
        hashtagList = Arrays.asList(mapper.readValue(params.get("hashtags"), String[].class));
        PostDto postDto = PostDto.builder()
                .content(params.get("content"))
                .hashtags(hashtagList)
                .isAuction(Boolean.TRUE)
                .build();
        Posts post = this.postService.addPost(postDto, user);
        ProductsDto productsDto = ProductsDto.builder()
                .name(params.get("name"))
                .description(params.get("description"))
                .imgFile(imgFile)
                .build();
        Products pro = this.productService.createProduct(productsDto);
        String time = params.get("startTime");
        auction.setStartTime(new Date(Long.parseLong(time)));
        String time2 = params.get("endTime");
        auction.setEndTime(new Date(Long.parseLong(time2)));
        auction.setBuyoutPrice(new BigDecimal(params.get("startingPrice")));
        auction.setStartingPrice(new BigDecimal(params.get("buyoutPrice")));
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
        Auction auction = this.auctionRepository.getauAuctionByID(au.getAuctionId());
        if (auction == null) {
            return false;
        }
        au.getPostDto().setId(auction.getPostId().getPostId());
        Posts post = this.postService.updatePost(au.getPostDto(), userID);
        if (post == null) {
            return false;
        }
        auction.setPostId(post);
        auction.setBuyoutPrice(au.getBuyoutPrice());
        auction.setStartTime(au.getStartTime());
        auction.setEndTime(au.getEndTime());
        auction.setStartingPrice(au.getStartingPrice());
        auction.setWinningBid(au.getWinningBid());
        return (this.auctionRepository.updateAuction(auction) == null ? false : true);
    }

    @Override
    public AuctionDto toAuctionDto(Auction auction) {
        if (auction == null) {
            return null;
        }
        AuctionDto auctionDto = AuctionDto.builder()
                .auctionId(auction.getAuctionId())
                .startTime(auction.getStartTime())
                .endTime(auction.getEndTime())
                .startingPrice(auction.getStartingPrice())
                .buyoutPrice(auction.getBuyoutPrice())
                .winningBid(auction.getWinningBid())
                .build();
        auctionDto.setPostDto(this.postService.toPostDto(auction.getPostId()));
        auctionDto.setProductsDto(this.productService.toProductDto(auction.getProductId()));
        if (auction.getWinnerUserId() != null) {
            auctionDto.setWinnerUser(this.userService.toUsersDto(auction.getWinnerUserId()));
            auctionDto.setWinningBid(auction.getWinningBid());
        }
        return auctionDto;
    }

    @Override
    public AuctionDto winningBid(Map<String, String> params) {
        Auction au=this.auctionRepository.getauAuctionByID(Integer.parseInt(params.get("id")));
        au.setWinningBid(new BigDecimal(params.get("bid")));
        return this.toAuctionDto(this.auctionRepository.updateAuction(au));
    }

}

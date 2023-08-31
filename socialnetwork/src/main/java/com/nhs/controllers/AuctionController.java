/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.controllers;

import com.nhs.dto.AuctionDto;
import com.nhs.pojo.Auction;
import com.nhs.pojo.Users;
import com.nhs.service.AuctionService;
import com.nhs.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/api/")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @Autowired
    private UserService userService;

    @GetMapping("auction/")
    @CrossOrigin
    public ResponseEntity<List<AuctionDto>> getAllAuctions() {
        return new ResponseEntity<>(this.auctionService.getAllAuctions(), HttpStatus.OK);
    }

    @PostMapping("auction/")
    @CrossOrigin
    public ResponseEntity<?> createAuction(@RequestBody AuctionDto auctionDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            System.out.println(auctionDto.getPostDto().getContent());
            Auction auction = this.auctionService.createAuction(auctionDto, currentUser);
            return new ResponseEntity<>("Auction is created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("auction/{id}/")
    @CrossOrigin
    public ResponseEntity<?> updateAuction(@RequestBody AuctionDto auctionDto,@PathVariable(value = "id") int AuctionID) {
        auctionDto.setAuctionId(AuctionID);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            System.out.println(auctionDto.getPostDto().getContent());
            boolean auction = this.auctionService.updateAuction(auctionDto, currentUser.getUserId());
            return (auction?new ResponseEntity<>("Auction is update", HttpStatus.CREATED)
                    :new ResponseEntity<>("You do not have permission to update auction", HttpStatus.CREATED));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

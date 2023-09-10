/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhs.dto.AuctionDto;
import com.nhs.dto.CommentDto;
import com.nhs.pojo.Auction;
import com.nhs.pojo.Users;
import com.nhs.service.AuctionService;
import com.nhs.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(path = "auction/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<?> createAuction(@RequestParam Map<String, String> params, @RequestPart("imgFile") MultipartFile imgFile) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            AuctionDto auctionDto = this.auctionService.toAuctionDto(this.auctionService.createAuction(params, currentUser, imgFile));
            if (auctionDto != null) {
                return new ResponseEntity<>(auctionDto, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("RequestRight", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("auction/{id}/")
    @CrossOrigin
    public ResponseEntity<?> updateAuction(@RequestBody AuctionDto auctionDto, @PathVariable(value = "id") int AuctionID) {
        auctionDto.setAuctionId(AuctionID);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            System.out.println(auctionDto.getPostDto().getContent());
            boolean auction = this.auctionService.updateAuction(auctionDto, currentUser.getUserId());
            return (auction ? new ResponseEntity<>("Auction is update", HttpStatus.CREATED)
                    : new ResponseEntity<>("You do not have permission to update auction", HttpStatus.CREATED));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("auction/{id}/winningBid/")
    @CrossOrigin
    public ResponseEntity<?> winningBid(@RequestParam Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            AuctionDto au = this.auctionService.winningBid(params, currentUser);
            if (au != null) {
                return new ResponseEntity<>(au, HttpStatus.OK);
            }
            return new ResponseEntity<>("You do not have permission to update auction", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("auction/{id}/choseWinner/")
    @CrossOrigin
    public ResponseEntity<?> choseWinner(@PathVariable(value = "id") int auctionID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.getUserByUsername(userDetails.getUsername());
            List<CommentDto> commentDtos = this.auctionService.choseWinner(auctionID);
            if (commentDtos != null) {
                return new ResponseEntity<>(commentDtos, HttpStatus.OK);
            }
            return new ResponseEntity<>("No one Auction", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("auction/{id}/choseWinner/")
    @CrossOrigin
    public ResponseEntity<?> Winner(@PathVariable(value = "id") int auctionID, @RequestParam Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Users userWinner = userService.getUserByID(Integer.parseInt(params.get("userId")));
            if (this.auctionService.winner(auctionID, userWinner)) {
                return new ResponseEntity<>("Suscessful", HttpStatus.OK);
            }
            return new ResponseEntity<>("failed server", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

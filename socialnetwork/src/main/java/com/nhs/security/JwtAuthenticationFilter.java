///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.nhs.security;
//
//
//import com.nhs.dto.JwtResponse;
//import com.nhs.service.UserService;
//import com.nhs.serviceImpl.UserServiceImpl;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
///**
// *
// * @author ADMIN
// */
//@RequiredArgsConstructor
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final JwtTokenProvider jwtTokenProvider;
//    private final UserService UserService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        // get token from request header
//        String token = getTokenFromRequest(request);
//
//        if (token != null && jwtTokenProvider.isTokenExpired(token)){
//            String refreshToken = request.getHeader("Refresh-token");
//
//            JwtResponse jwtResponse = jwtTokenProvider.refreshToken(refreshToken);
//
//        }else{
//            // validate token
//            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
//                // get username from token
//                String username = jwtTokenProvider.getUsername(token);
//                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//                    UserDetails userDetails = this.UserService.loadUserByUsername(username);
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//                }
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String getTokenFromRequest(HttpServletRequest request){
//        String bearerToken = request.getHeader("Authorization");
//
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}

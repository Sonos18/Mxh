/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.controllers;

import com.nhs.dto.loginDto;
import com.nhs.pojo.Users;
import com.nhs.security.JwtService;
import com.nhs.service.PostService;
import com.nhs.service.UserService;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ADMIN
 */
@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService JwtService;

    @Autowired
    private UserService UserService;
    
    @Autowired
    private PostService postService;

    @PostMapping(path = "/register/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @CrossOrigin
    public ResponseEntity<?> register(@RequestParam Map<String, String> params, @RequestPart MultipartFile file) throws Exception {
        return new ResponseEntity<>(this.UserService.addUsers(params, file), HttpStatus.CREATED);

    }

    @PostMapping("/login/")
    public ResponseEntity<?> login(@RequestBody @Valid loginDto loginDto, HttpServletResponse response) throws Exception {

        authenticate(loginDto.getUsername(), loginDto.getPassword());

        final UserDetails userDetails = UserService.loadUserByUsername(loginDto.getUsername());
        Users user = UserService.getUserByUsername(userDetails.getUsername());

        String jwtResponse = JwtService.generateTokenLogin(userDetails.getUsername());
        if (jwtResponse != null) {
            Cookie cookie = new Cookie("JWT_TOKEN", jwtResponse);
            cookie.setPath("/");
            cookie.setMaxAge(3600);

            response.addCookie(cookie);
            return ResponseEntity.ok().body(jwtResponse);
        } else {
            return ResponseEntity.badRequest().body("Username or password is invalid!");
        }

    }

    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Users> details(Principal user) {
        Users u = this.UserService.getUserByUsername(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    @GetMapping("/users/profile/")
    @CrossOrigin
    public ResponseEntity<?> profile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = UserService.getUserByUsername(userDetails.getUsername());
            return new ResponseEntity<>(this.postService.getPostsForUser(currentUser),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

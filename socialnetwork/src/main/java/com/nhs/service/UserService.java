/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.service;

import com.nhs.dto.UsersDto;
import com.nhs.pojo.Users;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
public interface UserService extends UserDetailsService {

    Users getUserByID(int id);

    Users getUserByUsername(String username);

    Users addUser(Users user);

    Users addUsers(Map<String, String> params, MultipartFile file);
    
    UsersDto toUsersDto(Users user);

}

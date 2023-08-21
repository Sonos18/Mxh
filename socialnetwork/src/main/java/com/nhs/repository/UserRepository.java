/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.repository;

import com.nhs.pojo.Users;


/**
 *
 * @author admin
 */
public interface UserRepository {
    Users addUser(Users u);
    Users getUserByID(int id);
    Users getUserByUsername(String username);
}

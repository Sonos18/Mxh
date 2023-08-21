/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.serviceImpl;

import com.nhs.pojo.Users;
import com.nhs.repository.UserRepository;
import com.nhs.service.UserService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users getUserByID(int id) {
        return this.userRepository.getUserByID(id);
    }

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        Users users = userRepository.getUserByUsername(user);

        if (users==null) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }

        Users u = userRepository.getUserByID(users.getUserId());

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public Users getUserByUsername(String username) {
        return this.userRepository.getUserByUsername(username);
    }

    @Override
    public Users addUser(Users user) {
        return this.addUser(user);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.nhs.repository.AdminRepository;
import com.nhs.service.AdminService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository AdminRepository;

    @Override
    public List<Object[]> stat(Map<String, String> params) {
        return AdminRepository.stat(params);
    }

    @Override
    public List<Object[]> statLike(Map<String, String> params) {
        return AdminRepository.statLike(params);
    }

    @Override
    public List<Object[]> statPost(Map<String, String> params) {
        return AdminRepository.statPost(params);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public interface AdminRepository {
    List<Object[]> stat(Map<String,String> params);
}

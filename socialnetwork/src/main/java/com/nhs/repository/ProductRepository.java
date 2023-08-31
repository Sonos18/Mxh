/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.repository;

import com.nhs.pojo.Products;

/**
 *
 * @author DELL
 */
public interface ProductRepository {
    Products getProductByID(int id);
    Products createProduct(Products pro);
}

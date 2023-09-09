/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.service;

import com.nhs.dto.ProductsDto;
import com.nhs.pojo.Products;

/**
 *
 * @author DELL
 */
public interface ProductService {
    ProductsDto getProductByID(int id);
    Products createProduct(ProductsDto pro);
    ProductsDto toProductDto(Products pro);
}

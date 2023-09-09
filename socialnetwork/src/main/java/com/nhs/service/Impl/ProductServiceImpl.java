/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhs.dto.ProductsDto;
import com.nhs.pojo.Products;
import com.nhs.repository.ProductRepository;
import com.nhs.service.ProductService;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public ProductsDto getProductByID(int id) {
        Products p = this.productRepository.getProductByID(id);
        if (p == null) {
            return null;
        }
        ProductsDto productsDto = ProductsDto.builder()
                .id(p.getId())
                .description(p.getDescription())
                .name(p.getName())
                .image(p.getImage())
                .build();
        return productsDto;
    }

    @Override
    public Products createProduct(ProductsDto pro) {
        if (pro.getImgFile() != null) {
            try {
                Map res = this.cloudinary.uploader().upload(pro.getImgFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                pro.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            pro.setImage(null);
        }
        Products product = new Products();
        product.setName(pro.getName());
        product.setDescription(pro.getDescription());
        product.setImage(pro.getImage());
        return this.productRepository.createProduct(product);
    }

    @Override
    public ProductsDto toProductDto(Products pro) {
        ProductsDto productDto=ProductsDto.builder()
                .description(pro.getDescription())
                .id(pro.getId())
                .image(pro.getImage())
                .name(pro.getName())
                .build();
        return productDto;
    }
    
}

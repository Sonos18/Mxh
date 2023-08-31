/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.nhs.dto.ProductsDto;
import com.nhs.pojo.Products;
import com.nhs.repository.ProductRepository;
import com.nhs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductsDto getProductByID(int id) {
        Products p=this.productRepository.getProductByID(id);
        if(p==null)
            return null;
        ProductsDto productsDto=ProductsDto.builder()
                .id(p.getId())
                .description(p.getDescription())
                .name(p.getName())
                .image(p.getImage())
                .build();
        return productsDto;
    }

    @Override
    public Products createProduct(ProductsDto pro) {
        Products product=new Products();
        product.setName(pro.getName());
        product.setDescription(pro.getDescription());
        product.setImage(pro.getImage());
        return this.productRepository.createProduct(product);
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.dto;

import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private Integer id;
    private Date createAt;
    private String content;
    private String file;
    private Boolean isLocked;
    private Integer userId;
    private MultipartFile imgFile;
    private List<String> hashTags;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.repository;

import com.nhs.pojo.Hashtags;

/**
 *
 * @author admin
 */
public interface HashtagRepository {

    Hashtags getHashtagByText(String text);

    Hashtags getHashtagByID(Integer id);

    boolean addHashtag(String h);

    boolean checkHastag(String text);
}

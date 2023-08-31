/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.service;

import com.nhs.dto.NotificationDto;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface NotificationService {
    boolean createNotification(Posts post,String action,Users user);
    List<NotificationDto> getNotificationForUser(Users user);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.repository;

import com.nhs.pojo.Notifications;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface NotificationRepository {

    Notifications createNotification(Notifications not);

    List<Notifications> getNotificationForUser(Posts p);
    
    Notifications getNotificationById(int id);
    
    Notifications update (Notifications not);
    
    boolean checkNotification (Posts post,Users user, String action);
}

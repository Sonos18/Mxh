/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.service.Impl;

import com.nhs.dto.NotificationDto;
import com.nhs.dto.PostDto;
import com.nhs.dto.UsersDto;
import com.nhs.pojo.Notifications;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import com.nhs.repository.NotificationRepository;
import com.nhs.service.NotificationService;
import com.nhs.service.PostService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notRe;
    @Autowired
    private PostService postService;

    @Override
    public boolean createNotification(Posts post, String action, Users user) {
        Notifications notification = new Notifications();
        notification.setActionType(action);
        notification.setUserId(user);
        notification.setIsRead(Boolean.FALSE);
        notification.setTargetId(post);
        return this.notRe.createNotification(notification) != null ? true : false;
    }

    @Override
    public List<NotificationDto> getNotificationForUser(Users user) {
        List<Notifications> not = this.notRe.getNotificationForUser(user);
        List<NotificationDto> notDtoList=new ArrayList<>();
        not.forEach(n -> {
            PostDto postDto=this.postService.toPostDto(n.getTargetId());
            UsersDto usersDto=UsersDto.builder()
                    .userId(n.getUserId().getUserId())
                    .username(n.getUserId().getUsername())
                    .build();
            NotificationDto notDto = NotificationDto.builder()
                    .notificationId(n.getNotificationId())
                    .actionType(n.getActionType())
                    .createdAt(n.getCreatedAt())
                    .isRead(n.getIsRead())
                    .targetId(postDto)
                    .userId(usersDto)
                    .build();
            notDtoList.add(notDto);
        });
        return notDtoList;
    }

}

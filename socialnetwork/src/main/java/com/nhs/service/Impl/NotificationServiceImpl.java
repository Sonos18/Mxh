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
import com.nhs.repository.PostRepository;
import com.nhs.service.NotificationService;
import com.nhs.service.PostService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    @Autowired
    private PostRepository postRepository;

    @Override
    public boolean createNotification(Posts post, String action, Users user) {
        if(!this.notRe.checkNotification(post, user, action)){
        Notifications notification = new Notifications();
        notification.setActionType(action);
        notification.setUserId(user);
        notification.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        notification.setIsRead(Boolean.FALSE);
        notification.setTargetId(post);
        return this.notRe.createNotification(notification) != null ? true : false;
        }
        return true;
    }

    @Override
    public List<NotificationDto> getNotificationForUser(Users user) {
        List<Posts> posts=postRepository.getPostsForUser(user);
        List<Notifications> notsList=new ArrayList<>();
        posts.forEach(p->{
            notsList.addAll(this.notRe.getNotificationForUser(p));
        });
        List<NotificationDto> notDtoList=new ArrayList<>();
        notsList.forEach(n -> {
            PostDto postDto=this.postService.toPostDto(n.getTargetId());
            UsersDto usersDto=UsersDto.builder()
                    .userId(n.getUserId().getUserId())
                    .username(n.getUserId().getUsername())
                    .avatar(n.getUserId().getAvatar())
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

    @Override
    public boolean isReaded(int id) {
        Notifications notification= this.notRe.getNotificationById(id);
        notification.setIsRead(Boolean.TRUE);
        if(this.notRe.update(notification)!=null)
            return true;
        return false;
    }

}

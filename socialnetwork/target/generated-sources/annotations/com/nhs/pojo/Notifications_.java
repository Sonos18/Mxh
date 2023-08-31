package com.nhs.pojo;

import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-08-31T10:42:27", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Notifications.class)
public class Notifications_ { 

    public static volatile SingularAttribute<Notifications, String> actionType;
    public static volatile SingularAttribute<Notifications, Date> createdAt;
    public static volatile SingularAttribute<Notifications, Posts> targetId;
    public static volatile SingularAttribute<Notifications, Boolean> isRead;
    public static volatile SingularAttribute<Notifications, Integer> notificationId;
    public static volatile SingularAttribute<Notifications, Users> userId;

}
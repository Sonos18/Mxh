package com.nhs.pojo;

import com.nhs.pojo.Auction;
import com.nhs.pojo.Comments;
import com.nhs.pojo.Likes;
import com.nhs.pojo.Notifications;
import com.nhs.pojo.Posts;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-10T03:43:17", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SetAttribute<Users, Notifications> notificationsSet;
    public static volatile SetAttribute<Users, Auction> auctionSet;
    public static volatile SetAttribute<Users, Comments> commentsSet;
    public static volatile SingularAttribute<Users, Date> createdAt;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> role;
    public static volatile SetAttribute<Users, Posts> postsSet;
    public static volatile SingularAttribute<Users, String> avatar;
    public static volatile SingularAttribute<Users, Integer> userId;
    public static volatile SetAttribute<Users, Likes> likesSet;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> username;

}
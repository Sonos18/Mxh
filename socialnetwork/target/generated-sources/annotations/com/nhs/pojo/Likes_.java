package com.nhs.pojo;

import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-03T13:13:33", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Likes.class)
public class Likes_ { 

    public static volatile SingularAttribute<Likes, Integer> id;
    public static volatile SingularAttribute<Likes, Posts> postId;
    public static volatile SingularAttribute<Likes, Users> userId;
    public static volatile SingularAttribute<Likes, Date> createAt;

}
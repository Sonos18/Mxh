package com.nhs.pojo;

import com.nhs.pojo.Hashtags;
import com.nhs.pojo.Users;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-08-22T00:20:42", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Posts.class)
public class Posts_ { 

    public static volatile SingularAttribute<Posts, Date> createdAt;
    public static volatile SingularAttribute<Posts, String> image;
    public static volatile SetAttribute<Posts, Hashtags> hashtagsSet;
    public static volatile SingularAttribute<Posts, Boolean> isLocked;
    public static volatile SetAttribute<Posts, Hashtags> hashtagsSet1;
    public static volatile SingularAttribute<Posts, Integer> postId;
    public static volatile SingularAttribute<Posts, Users> userId;
    public static volatile SingularAttribute<Posts, String> content;
    public static volatile SingularAttribute<Posts, Date> updatedAt;

}
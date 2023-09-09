package com.nhs.pojo;

import com.nhs.pojo.Comments;
import com.nhs.pojo.Posts;
import com.nhs.pojo.Users;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-10T03:43:17", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Comments.class)
public class Comments_ { 

    public static volatile SetAttribute<Comments, Comments> commentsSet;
    public static volatile SingularAttribute<Comments, Integer> commentId;
    public static volatile SingularAttribute<Comments, Comments> parentCommentId;
    public static volatile SingularAttribute<Comments, Posts> postId;
    public static volatile SingularAttribute<Comments, Users> userId;
    public static volatile SingularAttribute<Comments, Date> createAt;
    public static volatile SingularAttribute<Comments, String> content;

}
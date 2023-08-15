package com.nhs.pojo;

import com.nhs.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-08-15T21:12:37")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, String> file;
    public static volatile SingularAttribute<Post, Integer> id;
    public static volatile SingularAttribute<Post, User> userId;
    public static volatile SingularAttribute<Post, String> content;

}
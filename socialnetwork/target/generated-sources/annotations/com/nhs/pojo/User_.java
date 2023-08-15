package com.nhs.pojo;

import com.nhs.pojo.Post;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-08-15T21:12:37")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> role;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SetAttribute<User, Post> postSet;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Date> createAt;
    public static volatile SingularAttribute<User, String> username;

}
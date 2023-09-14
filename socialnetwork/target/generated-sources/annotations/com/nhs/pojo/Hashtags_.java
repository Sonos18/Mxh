package com.nhs.pojo;

import com.nhs.pojo.Posts;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-11T06:50:32", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Hashtags.class)
public class Hashtags_ { 

    public static volatile SingularAttribute<Hashtags, Date> createdAt;
    public static volatile SingularAttribute<Hashtags, Integer> hashtagId;
    public static volatile SetAttribute<Hashtags, Posts> postsSet;
    public static volatile SingularAttribute<Hashtags, String> hashtagText;

}
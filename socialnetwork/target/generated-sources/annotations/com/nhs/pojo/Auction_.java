package com.nhs.pojo;

import com.nhs.pojo.Posts;
import com.nhs.pojo.Products;
import com.nhs.pojo.Users;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-10T03:43:17", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Auction.class)
public class Auction_ { 

    public static volatile SingularAttribute<Auction, Integer> auctionId;
    public static volatile SingularAttribute<Auction, BigDecimal> buyoutPrice;
    public static volatile SingularAttribute<Auction, Users> winnerUserId;
    public static volatile SingularAttribute<Auction, BigDecimal> winningBid;
    public static volatile SingularAttribute<Auction, Products> productId;
    public static volatile SingularAttribute<Auction, Date> startTime;
    public static volatile SingularAttribute<Auction, Date> endTime;
    public static volatile SingularAttribute<Auction, BigDecimal> startingPrice;
    public static volatile SingularAttribute<Auction, Posts> postId;

}
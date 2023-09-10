/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.repository.Impl;

import com.nhs.pojo.Comments;
import com.nhs.pojo.Likes;
import com.nhs.pojo.Posts;
import com.nhs.repository.AdminRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ADMIN
 */
@Repository
@Transactional
public class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> stat(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> cr = builder.createQuery(Object[].class);

        Root rC = cr.from(Comments.class);

        String month = params.get("month");
        String year = params.get("year");
        if (params != null && year != null) {

            List<Predicate> predicates = new ArrayList<>();

            if (month != null && !month.isEmpty()) {
                predicates.add(builder.equal(builder.function("year", Integer.class, rC.get("createAt")),
                        Integer.parseInt(year)));
                Predicate Condition = builder.lessThan(builder.function("MONTH",
                        Integer.class, rC.get("createAt")), Integer.parseInt(month));

                predicates.add(Condition);
            }

            cr.where(predicates.toArray(Predicate[]::new));
            cr.multiselect(builder.function("month", Integer.class, rC.get("createAt")),
                    builder.count(rC.get("createAt"))
            );
            cr.groupBy(builder.function("month", Integer.class, rC.get("createAt")));
            cr.orderBy(builder.asc(rC.get("createAt")));
            org.hibernate.query.Query query = s.createQuery(cr);
            return query.getResultList();
        }
        return null;

    }

    @Override
    public List<Object[]> statLike(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> cr = builder.createQuery(Object[].class);

        Root rL = cr.from(Likes.class);

        String month = params.get("month");
        String year = params.get("year");
        if (params != null && year != null) {

            List<Predicate> predicates = new ArrayList<>();

            if (month != null && !month.isEmpty()) {
                predicates.add(builder.equal(builder.function("year", Integer.class, rL.get("createAt")),
                        Integer.parseInt(year)));
                Predicate Condition = builder.lessThan(builder.function("MONTH",
                        Integer.class, rL.get("createAt")), Integer.parseInt(month));

                predicates.add(Condition);
            }

            cr.where(predicates.toArray(Predicate[]::new));
            cr.multiselect(builder.function("month", Integer.class, rL.get("createAt")),
                    builder.count(rL.get("createAt"))
            );
            cr.groupBy(builder.function("month", Integer.class, rL.get("createAt")));
            cr.orderBy(builder.asc(rL.get("createAt")));
            org.hibernate.query.Query query = s.createQuery(cr);
            return query.getResultList();
        }
        return null;
    }

    @Override
    public List<Object[]> statPost(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> cr = builder.createQuery(Object[].class);

        Root rL = cr.from(Posts.class);

        String month = params.get("month");
        String year = params.get("year");
        if (params != null && year != null) {

            List<Predicate> predicates = new ArrayList<>();

            if (month != null && !month.isEmpty()) {
                predicates.add(builder.equal(builder.function("year", Integer.class, rL.get("createdAt")),
                        Integer.parseInt(year)));
                Predicate Condition = builder.lessThan(builder.function("MONTH",
                        Integer.class, rL.get("createdAt")), Integer.parseInt(month));

                predicates.add(Condition);
            }

            cr.where(predicates.toArray(Predicate[]::new));
            cr.multiselect(builder.function("month", Integer.class, rL.get("createdAt")),
                    builder.count(rL.get("createdAt"))
            );
            cr.groupBy(builder.function("month", Integer.class, rL.get("createdAt")));
            cr.orderBy(builder.asc(rL.get("createdAt")));
            org.hibernate.query.Query query = s.createQuery(cr);
            return query.getResultList();
        }
        return null;
    }

}

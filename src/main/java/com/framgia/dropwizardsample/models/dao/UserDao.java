package com.framgia.dropwizardsample.models.dao;

import com.framgia.dropwizardsample.models.entities.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 06/06/2018
 */
public class UserDao extends AbstractDAO<User> {
    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @SuppressWarnings("JpaQlInspection")
    public User findUserByUsernameAndPassword(String username, String password) {
        List<User> list =  currentSession().createQuery("FROM User U WHERE U.username = :username AND U.password = :password", User.class)
                .setParameter("username",username).setParameter("password", password).list();
        if (list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}

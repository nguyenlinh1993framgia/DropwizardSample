package com.framgia.dropwizardsample.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author: Linh Nguyen The
 * @email: nguyenlinh1993@gmail.com
 * @club: Green cactus team
 * Copyright (c) GC 2018
 * Create 4/24/2018
 */
public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}

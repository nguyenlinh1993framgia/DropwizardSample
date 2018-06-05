package com.framgia.dropwizardsample.models.dao;

import com.framgia.dropwizardsample.models.entities.IpAddress;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 05/06/2018
 */
public class IpAddressDao extends AbstractDAO<IpAddress> {
    public IpAddressDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @SuppressWarnings("JpaQlInspection")
    public List<IpAddress> getAllIpAddress() {
        return currentSession().createQuery("FROM IpAddress",IpAddress.class).getResultList();
    }

    public boolean saveIpAddress(IpAddress ipAddress) {
        return persist(ipAddress).getId()>0;
    }

    public IpAddress deleteIpAddress(long id) {
        IpAddress ipAddress = get(id);
        if (ipAddress!=null) {
            currentSession().remove(ipAddress);
        }
        return ipAddress;
    }
}

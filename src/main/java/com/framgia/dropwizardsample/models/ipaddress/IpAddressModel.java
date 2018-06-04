package com.framgia.dropwizardsample.models.ipaddress;

import com.framgia.dropwizardsample.models.HibernateUtils;
import com.framgia.dropwizardsample.models.entities.IpAddress;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 04/06/2018
 */
public class IpAddressModel {
    private static Logger logger = Logger.getLogger(IpAddressModel.class.getSimpleName());
    public List<IpAddress> getAllIpAddess(){
        Session session = HibernateUtils.getSession();
        List<IpAddress> res = new ArrayList<>();
        try {
            res.addAll(session.createQuery("FROM IpAddress ", IpAddress.class).getResultList());
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return res;
    }

    public boolean saveIpAddress(IpAddress ipAddress){
        Session session = HibernateUtils.getSession();
        long id;
        try {
            session.beginTransaction();
            id = (Long) session.save(ipAddress);
            ipAddress.setId(id);
            session.getTransaction().commit();
        }catch (Exception ex){
            id = -1;
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }

        return id > 0;
    }

    public IpAddress deleteIpAddress(long id){
        IpAddress ipAddress;
        Session session = HibernateUtils.getSession();
        try {
            session.beginTransaction();
            ipAddress = session.find(IpAddress.class,id);
            if (ipAddress==null){
                return null;
            }
            session.remove(ipAddress);
            session.getTransaction().commit();
        }catch (Exception ex){
            logger.error("#deleteIpAddress",ex);
            ipAddress = null;
            session.getTransaction().rollback();
        }
        finally {
            logger.info("#Close session");
            session.close();
        }

        return ipAddress;
    }

    public boolean updateIpAddress(IpAddress ipAddress){
        Session session = HibernateUtils.getSession();
        long id = 1;
        try {
            session.beginTransaction();
            session.update(ipAddress);
            session.getTransaction().commit();
        }catch (Exception ex){
            id = -1;
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }

        return id > 0;
    }
}

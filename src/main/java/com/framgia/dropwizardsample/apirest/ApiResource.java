package com.framgia.dropwizardsample.apirest;


import com.codahale.metrics.annotation.Timed;
import com.framgia.dropwizardsample.models.dao.IpAddressDao;
import com.framgia.dropwizardsample.models.entities.IpAddress;
import com.framgia.dropwizardsample.models.entities.User;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 04/06/2018
 */

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class ApiResource {
    private IpAddressDao ipAddressDao;
    public ApiResource(SessionFactory sessionFactory){
        ipAddressDao = new IpAddressDao(sessionFactory);
    }

    @GET
    @Path("/list-ip-address")
    @Timed
    @UnitOfWork
    public Response listAllIpAddress() {
        List<IpAddress> items = ipAddressDao.getAllIpAddress();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(true);
        baseResponse.setData(items);
        baseResponse.setMessages(null);
        return Response.ok(baseResponse).build();
    }

    @POST
    @Path("/save-ip-address")
    @Timed
    @UnitOfWork
    public Response saveIpAddress(IpAddress ipAddress){
        boolean success = ipAddressDao.saveIpAddress(ipAddress);
        BaseResponse baseResponse = new BaseResponse();
        List<String> messages = new ArrayList<>();
        if (success) {
            baseResponse.setStatus(true);
            baseResponse.setData(ipAddress);
            messages.add("Save Ip Address success");
            baseResponse.setMessages(messages);
        }
        else{
            baseResponse.setStatus(false);
            baseResponse.setData(null);
            messages.add("Save Ip Address failure");
            baseResponse.setMessages(messages);
        }
        return Response.ok(baseResponse).build();
    }

    @DELETE
    @Path("/delete-ip-address/{id}")
    @Timed
    @UnitOfWork
    public Response deleteIpAddress(@PathParam("id") long id){
        IpAddress ipAddress = ipAddressDao.deleteIpAddress(id);
        BaseResponse baseResponse = new BaseResponse();
        List<String> messages = new ArrayList<>();
        if (ipAddress!=null) {
            baseResponse.setStatus(true);
            baseResponse.setData(ipAddress);
            messages.add("Delete Ip Address success");
            baseResponse.setMessages(messages);
        }
        else{
            baseResponse.setStatus(false);
            baseResponse.setData(null);
            messages.add("Delete Ip Address failure");
            baseResponse.setMessages(messages);
        }
        return Response.ok(baseResponse).build();
    }

    @POST
    @Path("/update-ip-address")
    @Timed
    @UnitOfWork
    public Response updateIpAddress(IpAddress ipAddress){
        boolean success = ipAddressDao.saveIpAddress(ipAddress);
        BaseResponse baseResponse = new BaseResponse();
        List<String> messages = new ArrayList<>();
        if (success) {
            baseResponse.setStatus(true);
            baseResponse.setData(ipAddress);
            messages.add("Update Ip Address success");
            baseResponse.setMessages(messages);
        }
        else{
            baseResponse.setStatus(false);
            baseResponse.setData(null);
            messages.add("Update Ip Address failure");
            baseResponse.setMessages(messages);
        }
        return Response.ok(baseResponse).build();
    }
}

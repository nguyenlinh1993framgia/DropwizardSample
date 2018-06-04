package com.framgia.dropwizardsample.apirest;


import com.framgia.dropwizardsample.models.entities.IpAddress;
import com.framgia.dropwizardsample.models.ipaddress.IpAddressModel;

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
    private IpAddressModel ipAddressModel;
    public ApiResource(){
        super();
        ipAddressModel = new IpAddressModel();
    }

    @GET
    @Path("/list-ip-address")
    public Response listAllIpAddress() {
        List<IpAddress> items = ipAddressModel.getAllIpAddess();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(true);
        baseResponse.setData(items);
        baseResponse.setMessages(null);
        return Response.ok(baseResponse).build();
    }

    @POST
    @Path("/save-ip-address")
    public Response saveIpAddress(IpAddress ipAddress){
        boolean success = ipAddressModel.saveIpAddress(ipAddress);
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
    public Response deleteIpAddress(@PathParam("id") long id){
        IpAddress ipAddress = ipAddressModel.deleteIpAddress(id);
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
    public Response updateIpAddress(IpAddress ipAddress){
        boolean success = ipAddressModel.updateIpAddress(ipAddress);
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

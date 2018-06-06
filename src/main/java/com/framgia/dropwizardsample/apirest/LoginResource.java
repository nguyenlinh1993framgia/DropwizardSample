package com.framgia.dropwizardsample.apirest;

import com.framgia.dropwizardsample.models.entities.User;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 05/06/2018
 */


@Path("/login")
public class LoginResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@Auth User user){

        return "Login Success";
    }
}

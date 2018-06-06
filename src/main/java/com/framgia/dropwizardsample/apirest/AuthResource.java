package com.framgia.dropwizardsample.apirest;

import com.codahale.metrics.annotation.Timed;
import com.framgia.dropwizardsample.models.dao.UserDao;
import com.framgia.dropwizardsample.models.entities.User;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableMap;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 06/06/2018
 */
@Path("/auth")
@Produces(APPLICATION_JSON)
public class AuthResource {

    private final byte[] tokenSecret;
    public static final String SUBJECT = "framgia-sample-auth";
    public static final long M_60_MINUTES = 3600000L;

    private UserDao userDao;

    public AuthResource(byte[] tokenSecret, SessionFactory sessionFactory) {
        this.tokenSecret = tokenSecret;
        this.userDao = new UserDao(sessionFactory);
    }

    @POST
    @Path("/login")
    @Timed
    @UnitOfWork
    public Response login(User user) {
        user = userDao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        BaseResponse baseResponse = new BaseResponse();
        List<String> messages = new ArrayList<>();
        if (user != null) {
            Map<String, String> mapToken = generateValidToken(user);
            baseResponse.setStatus(true);
            baseResponse.setData(mapToken);
            messages.add("Login success");
            baseResponse.setMessages(messages);
        } else {
            baseResponse.setStatus(false);
            baseResponse.setData(null);
            messages.add("Login Failure");
            baseResponse.setMessages(messages);
        }
        return Response.ok(baseResponse).build();
    }

    @GET
    @Path("/generate-expired-token")
    public Map<String, String> generateExpiredToken() {
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(-20);
        claims.setSubject(SUBJECT);

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(tokenSecret));

        try {
            return singletonMap("token", jws.getCompactSerialization());
        } catch (JoseException e) {
            throw Throwables.propagate(e);
        }
    }

    public Map<String, String> generateValidToken(User user) {
        final JwtClaims claims = new JwtClaims();
        claims.setSubject(SUBJECT);
        long timeExpired = System.currentTimeMillis() + M_60_MINUTES;
        claims.setExpirationTimeMinutesInTheFuture(60);
        claims.setClaim("userId", user.getId());
        claims.setClaim("username", user.getUsername());
        claims.setClaim("email", user.getEmail());

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(tokenSecret));

        try {
            return ImmutableMap.of("token", jws.getCompactSerialization(), "expired", String.valueOf(timeExpired));
        } catch (JoseException e) {
            throw Throwables.propagate(e);
        }
    }

    @GET
    @Path("/check-token")
    public Map<String, Object> get(@Auth User user) {
        return ImmutableMap.of("id", user.getId(), "username", user.getUsername(), "email", user.getEmail());
    }
}

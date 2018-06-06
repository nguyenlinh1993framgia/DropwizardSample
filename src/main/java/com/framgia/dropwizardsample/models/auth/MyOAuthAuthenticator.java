package com.framgia.dropwizardsample.models.auth;

import com.framgia.dropwizardsample.apirest.AuthResource;
import com.framgia.dropwizardsample.models.entities.User;
import io.dropwizard.auth.Authenticator;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtContext;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 05/06/2018
 */
public class MyOAuthAuthenticator implements Authenticator<JwtContext, User> {

    @Override
    public Optional<User> authenticate(JwtContext context) {
        // Provide your own implementation to lookup users based on the principal attribute in the
        // JWT Token. E.g.: lookup users from a database etc.
        // This method will be called once the token's signature has been verified

        // In case you want to verify different parts of the token you can do that here.
        // E.g.: Verifying that the provided token has not expired.

        // All JsonWebTokenExceptions will result in a 401 Unauthorized response.

        try {
            final String subject = context.getJwtClaims().getSubject();
            if (AuthResource.SUBJECT.equals(subject)) {
                long id = (Long) context.getJwtClaims().getClaimsMap().get("userId");
                String username = (String) context.getJwtClaims().getClaimsMap().get("username");
                String email = (String) context.getJwtClaims().getClaimsMap().get("email");
                return Optional.of(new User(id, username, email));
            }
            return Optional.empty();
        } catch (MalformedClaimException e) {
            return Optional.empty();
        }
    }
}

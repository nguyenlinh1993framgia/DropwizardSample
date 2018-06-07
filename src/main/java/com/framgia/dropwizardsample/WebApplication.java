package com.framgia.dropwizardsample;

import com.framgia.dropwizardsample.apirest.ApiResource;
import com.framgia.dropwizardsample.apirest.AuthResource;
import com.framgia.dropwizardsample.apirest.LoginResource;
import com.framgia.dropwizardsample.models.auth.MyOAuthAuthenticator;
import com.framgia.dropwizardsample.models.entities.IpAddress;
import com.framgia.dropwizardsample.models.entities.User;
import com.framgia.dropwizardsample.models.jwt.JwtAuthFilter;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 04/06/2018
 */
public class WebApplication extends Application<WebApplicationConfiguration> {

    private final HibernateBundle<WebApplicationConfiguration> hibernate = new HibernateBundle<WebApplicationConfiguration>(IpAddress.class, User.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(WebApplicationConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<WebApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    public static void main(String[] args) throws Exception {
        new WebApplication().run(args);
    }

    public void run(WebApplicationConfiguration configuration, Environment environment) throws Exception {

        final byte[] key = configuration.getJwtTokenSecret();

        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(new HmacKey(key)) // verify the signature with the public key
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build(); // create the JwtConsumer instance

        environment.jersey().register(new AuthDynamicFeature(
                new JwtAuthFilter.Builder<User>()
                        .setJwtConsumer(consumer)
                        .setRealm("realm")
                        .setPrefix("Bearer")
                        .setAuthenticator(new MyOAuthAuthenticator())
                        .buildAuthFilter()));

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthResource(configuration.getJwtTokenSecret(), hibernate.getSessionFactory()));

        //Resource
        environment.jersey().register(new ApiResource(hibernate.getSessionFactory()));
        environment.jersey().register(LoginResource.class);
    }
}

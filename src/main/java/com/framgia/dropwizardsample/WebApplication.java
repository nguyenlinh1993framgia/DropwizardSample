package com.framgia.dropwizardsample;

import com.framgia.dropwizardsample.apirest.ApiResource;
import com.framgia.dropwizardsample.models.entities.IpAddress;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 04/06/2018
 */
public class WebApplication extends Application<WebApplicationConfiguration> {

    private final HibernateBundle<WebApplicationConfiguration> hibernate = new HibernateBundle<WebApplicationConfiguration>(IpAddress.class) {
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

    public void run(WebApplicationConfiguration webApplicationConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new ApiResource(hibernate.getSessionFactory()));
    }
}

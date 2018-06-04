package com.framgia.dropwizardsample;

import io.dropwizard.Configuration;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 04/06/2018
 */
public class WebApplicationConfiguration extends Configuration {

    private String myConfig;

    public String getMyConfig() {
        return myConfig;
    }

    public void setMyConfig(String myConfig) {
        this.myConfig = myConfig;
    }
}

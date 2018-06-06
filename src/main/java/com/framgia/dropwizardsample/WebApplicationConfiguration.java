package com.framgia.dropwizardsample;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 04/06/2018
 */
public class WebApplicationConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @NotEmpty
    private String jwtTokenSecret = "dfwzsdzwh823zebdwdz772632gdsbdz8ads89975zpp5oapcvbmzbekieushnzoadsfjksj9a8726afzzzasfd445sm7alo89";

    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtTokenSecret.getBytes("UTF-8");
    }

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    private String myConfig;

    public String getMyConfig() {
        return myConfig;
    }

    public void setMyConfig(String myConfig) {
        this.myConfig = myConfig;
    }


}

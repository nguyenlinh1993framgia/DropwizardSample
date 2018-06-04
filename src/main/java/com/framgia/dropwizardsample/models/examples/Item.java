package com.framgia.dropwizardsample.models.examples;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 04/06/2018
 */
public class Item {

    @JsonProperty
    private int id;

    @JsonProperty
    private String name;

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

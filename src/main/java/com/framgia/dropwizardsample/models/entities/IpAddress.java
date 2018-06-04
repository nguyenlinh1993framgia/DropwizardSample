package com.framgia.dropwizardsample.models.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 04/06/2018
 */

@Entity
@Table(name = "ip_address")
public class IpAddress {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long id;

    @Column(name = "ip")
    private String ip;


    @Column(name = "country")
    private String country;


    public IpAddress() {

    }

    public IpAddress(String ip) {
        this.ip = ip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}

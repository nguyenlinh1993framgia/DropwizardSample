package com.framgia.dropwizardsample.apirest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author: Linh Nguyen The
 * @email: nguyen.the.linhb@framgia.com
 * @company: Framgia
 * Copyright (c) Framgia 2018
 * Create 04/06/2018
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    @JsonProperty("status")
    private boolean status;

    @JsonProperty("data")
    private Object data;

    @JsonProperty("messages")
    private List<String> messages;

    public void setData(Object data) {
        this.data = data;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}

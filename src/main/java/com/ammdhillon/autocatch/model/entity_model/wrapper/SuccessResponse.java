package com.ammdhillon.autocatch.model.entity_model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SuccessResponse extends CommonResponse {

    private final Object data;

    public SuccessResponse(@JsonProperty("status") Integer status,
                           @JsonProperty("logout") Boolean logout,
                           @JsonProperty("data") Object data) {
        this.status = status;
        this.logout = logout;
        this.data = data;
    }
}

package com.ammdhillon.autocatch.model.entity_model.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorResponse extends CommonResponse {

    private final String error;

    public ErrorResponse(@JsonProperty("status") Integer status,
                         @JsonProperty("logout") Boolean logout,
                         @JsonProperty("error") String error) {
        this.status = status;
        this.logout = logout;
        this.error = error;
    }
}

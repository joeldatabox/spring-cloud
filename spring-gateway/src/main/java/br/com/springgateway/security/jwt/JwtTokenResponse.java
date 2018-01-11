package br.com.springgateway.security.jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by joel on 26/03/17.
 */
public final class JwtTokenResponse implements Serializable {
    private final String token;

    @JsonCreator
    public JwtTokenResponse(@JsonProperty("token") final String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}

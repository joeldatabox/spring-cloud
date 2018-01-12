package br.com.springgateway.security.service;

import br.com.springmodel.security.jwt.JwtUser;
import br.com.springmodel.security.model.User;

/**
 * @author Joel Rodrigues Moreira on 09/01/18.
 * @project demo
 */
public interface CacheUserAuthenticationService {
    void set(final String token, final JwtUser user);

    JwtUser get(final String token);
}

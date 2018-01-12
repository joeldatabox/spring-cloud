package br.com.springbasicsecurity.exception;

import br.com.springmodel.security.model.Authority;
import br.com.springmodel.security.model.User;
import br.com.springmodel.security.model.enumeration.Authorities;
import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class SecurityCredentialException extends SecurityUnauthorizedException {

    public SecurityCredentialException(final String message, Authority authority) {
        this(message, authority.getName());
    }

    public SecurityCredentialException(final String message, Authorities authority) {
        super(message, HttpStatus.FORBIDDEN, User.class, "authorities", (authority != null ? authority.name() : ""));
    }
}

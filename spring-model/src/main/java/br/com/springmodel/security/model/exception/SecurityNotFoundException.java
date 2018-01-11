package br.com.springmodel.security.model.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class SecurityNotFoundException extends SecurityUnauthorizedException {

    public SecurityNotFoundException(final Throwable cause) {
        super(cause);
    }

    public SecurityNotFoundException(final Class clazz, final String field, final String message) {
        super("AbstractResource Not Found", HttpStatus.NOT_FOUND, clazz, field, message);
    }
}

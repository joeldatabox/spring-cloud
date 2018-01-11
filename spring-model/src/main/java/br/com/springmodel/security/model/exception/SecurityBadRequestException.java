package br.com.springmodel.security.model.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class SecurityBadRequestException extends SecurityUnauthorizedException {

    public SecurityBadRequestException(final Class clazz, final String field, final String message) {
        super("Bad Request", HttpStatus.BAD_REQUEST, clazz, field, message);
    }
}

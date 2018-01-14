package br.com.springexception.throwables.security;

import org.springframework.http.HttpStatus;

/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
public class SpringBootSecurityBadRequestException extends SpringBootSecurityUnauthorizedException {

    public SpringBootSecurityBadRequestException(final Class clazz, final String field, final String message) {
        super("Bad Request", HttpStatus.BAD_REQUEST, clazz, field, message);
    }
}

package br.com.springexception.throwables.security;

import org.springframework.http.HttpStatus;
/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
public class SpringBootSecurityNotFoundException extends SpringBootSecurityUnauthorizedException {

    public SpringBootSecurityNotFoundException(final Throwable cause) {
        super(cause);
    }

    public SpringBootSecurityNotFoundException(final Class clazz, final String field, final String message) {
        super("AbstractResource Not Found", HttpStatus.NOT_FOUND, clazz, field, message);
    }
}

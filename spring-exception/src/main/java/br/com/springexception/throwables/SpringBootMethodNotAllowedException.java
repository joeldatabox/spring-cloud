package br.com.springexception.throwables;

import org.springframework.http.HttpStatus;
/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */

public class SpringBootMethodNotAllowedException extends SpringBootException {


    public SpringBootMethodNotAllowedException(final String message) {
        super(message, HttpStatus.METHOD_NOT_ALLOWED);
    }

    public SpringBootMethodNotAllowedException(final Class clazz, final String field, final String message) {
        super("Method not allowed", HttpStatus.METHOD_NOT_ALLOWED, clazz, field, message);
    }
}

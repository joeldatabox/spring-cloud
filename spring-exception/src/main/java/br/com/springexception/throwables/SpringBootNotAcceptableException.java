package br.com.springexception.throwables;

import org.springframework.http.HttpStatus;
/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */

public class SpringBootNotAcceptableException extends SpringBootException {

    public SpringBootNotAcceptableException(final Class clazz, final String field, final String message) {
        super("Not Acceptable", HttpStatus.NOT_ACCEPTABLE, clazz, field, message);
    }
}

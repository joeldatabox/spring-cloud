package br.com.springexception.throwables;

import org.springframework.http.HttpStatus;

/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */

public class SpringBootNoContentException extends SpringBootException {

    public SpringBootNoContentException(final Class clazz, final String field, final String message) {
        super("No Content", HttpStatus.NO_CONTENT, clazz, field, message);
    }
}

package br.com.springexception.throwables.repository;

import br.com.springexception.throwables.SpringBootException;
import org.springframework.http.HttpStatus;

/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
public class SpringBootRepositoryException extends SpringBootException {

    public SpringBootRepositoryException(final Class clazz, final String field, final String message) {
        super("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, clazz, field, message);
        /*this.message = "Conflict";
        this.status = HttpStatus.CONFLICT;
        this.objectName = clazz.getSimpleName().toLowerCase();
        this.details.put(this.objectName + "." + field, message);*/
    }
}

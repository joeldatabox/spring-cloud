package br.com.springmodel.security.model.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class SecurityConflictException extends SecurityUnauthorizedException {

    public SecurityConflictException(final Class clazz, final String field, final String message) {
        super("Conflict", HttpStatus.CONFLICT, clazz, field, message);
        /*this.message = "Conflict";
        this.status = HttpStatus.CONFLICT;
        this.objectName = clazz.getSimpleName().toLowerCase();
        this.details.put(this.objectName + "." + field, message);*/
    }
}

package br.com.springexception.throwables;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */

public class SpringBootException extends RuntimeException {
    protected final HttpStatus status;
    protected final String message;
    protected String objectName;
    protected Map<String, Object> details = new HashMap<>();

    public SpringBootException() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "ERROR *-*";
        this.objectName = "unknow :(";
    }

    public SpringBootException(final String message, final HttpStatus status, final Class clazz, final String field, final String info) {
        this.message = message;
        this.status = status;
        /*if (clazz != null) {
            this.objectName = clazz.getSimpleName().toLowerCase();
            this.details.put(this.objectName + "." + field, info);
        }*/
        if (clazz != null) {
            this.objectName = clazz.getSimpleName().toLowerCase();
        }
        if (field != null) {
            this.details.put(this.objectName + "." + field, info);
        }
    }

    @JsonCreator
    public SpringBootException(
            @JsonProperty final HttpStatus status,
            @JsonProperty final String message,
            @JsonProperty final String objectName,
            @JsonProperty final Map<String, Object> details) {
        this.status = status;
        this.message = message;
        this.objectName = objectName;
        this.details = details;
    }


    public SpringBootException(final String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public SpringBootException(final String message, final Throwable cause) {
        super(message, cause);
        this.message = message;
        this.objectName = "unknow :(";
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public SpringBootException(final String message, final HttpStatus status) {
        super(message);
        this.message = message;
        this.objectName = "unknow :(";
        this.status = status;
    }

    public SpringBootException(final Throwable cause) {
        super(cause);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "ERROR *-*";
        this.objectName = "unknow :(";
    }

    public SpringBootException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.objectName = "unknow :(";
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getObjectName() {
        return objectName;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public SpringBootException addDetails(final String key, final Object value) {
        this.details.put(key, value);
        return this;
    }

    public SpringBootException addDetails(final String key, final List<Object> value) {
        this.details.put(key, value);
        return this;
    }

    public SpringBootException addDetails(final Map<String, Object> details) {
        this.details.putAll(details);
        return this;
    }

    public boolean containsDetais() {
        return !this.details.isEmpty();
    }
}

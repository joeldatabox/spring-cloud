package br.com.springexception.handlers;

import br.com.springexception.throwables.SpringBootException;
import br.com.springexception.throwables.repository.SpringBootRepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joel on 14/04/17.
 */
@ControllerAdvice
@RestController
public class SpringBootRepositoryExceptionHandler {

    private ErrorMessageBuilder errorMessageBuilder;

    @Autowired
    public SpringBootRepositoryExceptionHandler(final ErrorMessageBuilder errorMessageBuilder) {
        this.errorMessageBuilder = errorMessageBuilder;
    }

    @ExceptionHandler(value = SpringBootException.class)
    public ResponseEntity SpringBootException(final SpringBootRepositoryException ex) {
        return errorMessageBuilder.build(ex).toResponseEntity();
    }

}

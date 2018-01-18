package br.com.springexception.service;

import br.com.springexception.throwables.SpringBootException;
import br.com.springexception.throwables.repository.SpringBootRepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * A classe começa com o nome Ex1_ por conta de precedencia de exceptions do Spring
 *
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
@ControllerAdvice
@RestController
public class Ex1_SpringBootRepositoryExceptionHandler {

    private ErrorMessageBuilder errorMessageBuilder;

    @Autowired
    public Ex1_SpringBootRepositoryExceptionHandler(final ErrorMessageBuilder errorMessageBuilder) {
        this.errorMessageBuilder = errorMessageBuilder;
    }

    @ExceptionHandler(value = SpringBootException.class)
    public ResponseEntity SpringBootException(final SpringBootRepositoryException ex) {
        return errorMessageBuilder.build(ex).toResponseEntity();
    }

}

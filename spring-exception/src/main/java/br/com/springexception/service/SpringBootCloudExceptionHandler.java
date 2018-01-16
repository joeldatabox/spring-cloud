package br.com.springexception.service;

import br.com.springexception.throwables.SpringBootException;
import br.com.springexception.throwables.SpringBootNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * Created by joel on 14/04/17.
 */
@ControllerAdvice
@RestController
public class SpringBootCloudExceptionHandler {

    private final ErrorMessageBuilder errorMessageBuilder;

    @Autowired
    public SpringBootCloudExceptionHandler(final ErrorMessageBuilder errorMessageBuilder) {
        this.errorMessageBuilder = errorMessageBuilder;
    }

    @ExceptionHandler(value = SpringBootException.class)
    public ResponseEntity SpringBootException(final SpringBootException ex) {
        return errorMessageBuilder.build(ex).toResponseEntity();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity not(final ResourceNotFoundException ex) {
        return errorMessageBuilder.build(new SpringBootNotFoundException(ex)).toResponseEntity();
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity not(final NoHandlerFoundException ex) {
        return errorMessageBuilder.build(new SpringBootNotFoundException(ex)).toResponseEntity();
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity validationException(final ConstraintViolationException ex) {
        return errorMessageBuilder.build(ex).toResponseEntity();
    }


}

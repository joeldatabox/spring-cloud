package br.com.springexception.service;

import br.com.springexception.throwables.security.SpringBootSecurityUnauthorizedException;
import br.com.springexception.throwables.security.SpringBootSecurityUserNameOrPasswordInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joel on 14/04/17.
 */
@ControllerAdvice
@RestController
public class SpringBootSecurityExceptionHandler {

    private final ErrorMessageBuilder errorMessageBuilder;

    @Autowired
    public SpringBootSecurityExceptionHandler(final ErrorMessageBuilder errorMessageBuilder) {
        this.errorMessageBuilder = errorMessageBuilder;
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity usernameNotFoundException(final UsernameNotFoundException ex) {
        SpringBootSecurityUserNameOrPasswordInvalidException exx = new SpringBootSecurityUserNameOrPasswordInvalidException();
        exx.addSuppressed(ex);
        return usernameNotFoundException(exx);
    }

    @ExceptionHandler(value = SpringBootSecurityUserNameOrPasswordInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity usernameNotFoundException(final SpringBootSecurityUserNameOrPasswordInvalidException ex) {
        return errorMessageBuilder.build(ex).toResponseEntity();
    }

    @ExceptionHandler(value = SpringBootSecurityUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity usernameNotFoundException(final SpringBootSecurityUnauthorizedException ex) {
        return errorMessageBuilder.build(ex).toResponseEntity();
    }
}

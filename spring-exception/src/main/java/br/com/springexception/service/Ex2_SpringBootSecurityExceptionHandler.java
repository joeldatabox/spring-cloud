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
 * A classe começa com o nome Ex2_ por conta de precedencia de exceptions do Spring
 *
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
@ControllerAdvice
@RestController
public class Ex2_SpringBootSecurityExceptionHandler {

    private final ErrorMessageBuilder errorMessageBuilder;

    @Autowired
    public Ex2_SpringBootSecurityExceptionHandler(final ErrorMessageBuilder errorMessageBuilder) {
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

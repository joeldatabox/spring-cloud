package br.com.springgateway.security.jwt.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by joel on 17/01/17.
 */
public class SecurityUserNameOrPasswordInvalidException extends SecurityUnauthorizedException {

    public SecurityUserNameOrPasswordInvalidException() {
        this("Usuário e/ou senha incorreto(s)");
        this.status = HttpStatus.UNAUTHORIZED;
    }

    public SecurityUserNameOrPasswordInvalidException(final String message) {
        super(message);
        this.status = HttpStatus.UNAUTHORIZED;
    }
}

package br.com.springexception.throwables.security;

import org.springframework.http.HttpStatus;

/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
public class SpringBootSecurityUserNameOrPasswordInvalidException extends SpringBootSecurityUnauthorizedException {

    public SpringBootSecurityUserNameOrPasswordInvalidException() {
        this("Usuário e/ou senha incorreto(s)");
        this.status = HttpStatus.UNAUTHORIZED;
    }

    public SpringBootSecurityUserNameOrPasswordInvalidException(final String message) {
        super(message);
        this.status = HttpStatus.UNAUTHORIZED;
    }
}

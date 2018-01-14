package br.com.springexception.throwables.security;

import org.springframework.http.HttpStatus;
/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
public class SpringBootSecurityCredentialException extends SpringBootSecurityUnauthorizedException {

    public SpringBootSecurityCredentialException(final String message, String authority) {
        super(message, HttpStatus.FORBIDDEN, null, "authorities", (authority != null ? authority : ""));
    }
}

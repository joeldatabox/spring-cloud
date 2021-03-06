package br.com.springbasicsecurity.infra.component;

import br.com.springexception.throwables.security.SpringBootSecurityUnauthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Joel Rodrigues Moreira on 12/01/18.
 * @project spring-cloud
 */
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint, Serializable {

    protected final String urlLogin;

    public UnauthorizedHandler(@Value("${springboot.security.jwt.controller.loginEndPoint}") final String urlLogin) {
        this.urlLogin = urlLogin;
    }

    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter()
                .print(new SpringBootSecurityUnauthorizedException("Unauthorized!")
                        .addDetails("urlLogin", request
                                .getRequestURL()
                                .toString()
                                .replace(request.getRequestURI(), "") + urlLogin
                        ).toJson());

    }
}
package br.com.springpessoa.interceptor;

import br.com.springbasicsecurity.infra.service.UserService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Joel Rodrigues Moreira on 23/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    private final UserService service;
    private final String tokenHeader;

    @Autowired
    public FeignInterceptor(@Value("${springboot.security.jwt.controller.tokenHeader}") final String tokenHeader, final UserService service) {
        this.tokenHeader = tokenHeader;
        this.service = service;
    }

    @Override
    public void apply(final RequestTemplate template) {
        template.header(this.tokenHeader, this.service.getCurrentToken().getToken());
    }
}

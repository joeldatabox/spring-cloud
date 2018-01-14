package br.com.springgateway.security.config;

import br.com.springbasicsecurity.component.AuthenticationTokenFilter;
import br.com.springbasicsecurity.component.UnauthorizedHandler;
import br.com.springbasicsecurity.component.util.JwtTokenUtil;
import br.com.springbasicsecurity.service.CacheUserAuthenticationService;
import br.com.springmodel.security.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
@Configuration
public class SecurityConfig {

    @Bean
    @Autowired
    public AuthenticationTokenFilter createAuthenticationTokenFilter(
            final UserDetailsService detailsService,
            final JwtTokenUtil tokenUtil,
            @Value("${springboot.security.jwt.controller.tokenHeader}") final String tokenHeader,
            final CacheUserAuthenticationService cacheAuth) {
        return new AuthenticationTokenFilter(detailsService, tokenUtil, tokenHeader, cacheAuth);
    }

    @Bean
    public UnauthorizedHandler createUnauthorizedHandler(@Value("${springboot.security.jwt.controller.loginEndPoint}") final String urlLogin) {
        return new UnauthorizedHandler(urlLogin);
    }

    @Bean
    @Autowired
    public JwtTokenUtil createJwtTokenUtil(final SecretService secretService) {
        return new JwtTokenUtil(secretService);
    }

    @Bean
    public SecretService createSecretService() {
        return new SecretService();
    }
}

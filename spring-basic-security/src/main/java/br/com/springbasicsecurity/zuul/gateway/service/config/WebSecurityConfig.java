package br.com.springbasicsecurity.zuul.gateway.service.config;

import br.com.springbasicsecurity.infra.component.AuthenticationTokenFilterGateway;
import br.com.springbasicsecurity.infra.component.UnauthorizedHandler;
import br.com.springbasicsecurity.infra.component.util.JwtTokenUtil;
import br.com.springbasicsecurity.infra.repository.UserRepository;
import br.com.springbasicsecurity.infra.service.CacheUserAuthenticationService;
import br.com.springbasicsecurity.infra.service.UserService;
import br.com.springbasicsecurity.infra.service.impl.CacheUserAuthenticationServiceImpl;
import br.com.springbasicsecurity.infra.service.impl.UserDetailServiceImpl;
import br.com.springbasicsecurity.infra.service.impl.UserServiceImpl;
import br.com.springmodel.security.service.SecretService;
import br.com.springredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Configurações dos beans necessários para segurança do gateway
 *
 * @author Joel Rodrigues Moreira on 16/01/18.
 * @project spring-cloud
 */
@Configuration
public class WebSecurityConfig {

    @Bean
    @Autowired
    public AuthenticationTokenFilterGateway createAuthenticationTokenFilter(
            final UserDetailsService detailsService,
            final JwtTokenUtil tokenUtil,
            @Value("${springboot.security.jwt.controller.tokenHeader}") final String tokenHeader,
            final CacheUserAuthenticationService cacheAuth) {
        return new AuthenticationTokenFilterGateway(detailsService, tokenUtil, tokenHeader, cacheAuth);
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

    @Bean
    @Autowired
    public UserDetailsService createUserDetailsService(final UserRepository repository) {
        return new UserDetailServiceImpl(repository);
    }

    @Bean
    @Autowired
    public CacheUserAuthenticationService createCacheUserAuthenticationService(final RedisService redisService) {
        return new CacheUserAuthenticationServiceImpl(redisService);
    }

    @Bean
    @Autowired
    public UserService createUserService(final UserRepository repository, @Value("${springboot.security.jwt.controller.tokenHeader}") final String tokenHeader) {
        return new UserServiceImpl(repository, tokenHeader);
    }
}

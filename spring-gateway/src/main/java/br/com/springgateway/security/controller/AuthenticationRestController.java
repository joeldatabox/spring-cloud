package br.com.springgateway.security.controller;

import br.com.springbasicsecurity.component.util.JwtTokenUtil;
import br.com.springbasicsecurity.response.JwtTokenResponse;
import br.com.springbasicsecurity.service.CacheUserAuthenticationService;
import br.com.springmodel.security.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Joel Rodrigues on 26/03/17.
 * RestController responsavel por despachar novas requisições de token
 */
@RestController
public class AuthenticationRestController extends br.com.springbasicsecurity.controller.AuthenticationRestController {
    final CacheUserAuthenticationService cacheAuth;

    @Autowired
    public AuthenticationRestController(
            @Value("${springboot.security.jwt.controller.tokenHeader}") final String tokenHeader,
            final AuthenticationManager authenticationManager,
            final JwtTokenUtil jwtTokenUtil,
            final UserDetailsService userDetailsService,
            final CacheUserAuthenticationService cacheAuth) {
        super(tokenHeader, authenticationManager, jwtTokenUtil, userDetailsService);
        this.cacheAuth = cacheAuth;
    }

    @Override
    protected void afterGeneratedToken(final JwtUser user, final JwtTokenResponse token) {
        this.cacheAuth.set(token.getToken(), user);
    }
}

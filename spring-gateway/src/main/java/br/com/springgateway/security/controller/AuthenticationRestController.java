package br.com.springgateway.security.controller;

import br.com.springbasicsecurity.infra.component.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController responsavel por despachar novas requisições de token
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
@RestController
public class AuthenticationRestController extends br.com.springbasicsecurity.infra.controller.AuthenticationRestController {

    @Autowired
    public AuthenticationRestController(
            @Value("${springboot.security.jwt.controller.tokenHeader}") final String tokenHeader,
            final AuthenticationManager authenticationManager,
            final JwtTokenUtil jwtTokenUtil,
            final UserDetailsService userDetailsService) {
        super(tokenHeader, authenticationManager, jwtTokenUtil, userDetailsService);
    }
}

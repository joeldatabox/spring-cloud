package br.com.springgateway.security.controller;

import br.com.springbasicsecurity.component.util.JwtTokenUtil;
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
    @Autowired
    public AuthenticationRestController(
            @Value("${springboot.security.jwt.controller.tokenHeader}") final String tokenHeader,
            final AuthenticationManager authenticationManager,
            final JwtTokenUtil jwtTokenUtil,
            final UserDetailsService userDetailsService) {
        super(tokenHeader, authenticationManager, jwtTokenUtil, userDetailsService);
    }
}

package br.com.springpessoa.security;

import br.com.springbasicsecurity.infra.component.util.JwtTokenUtil;
import br.com.springbasicsecurity.infra.service.CacheUserAuthenticationService;
import br.com.springbasicsecurity.infra.service.impl.CacheUserAuthenticationServiceImpl;
import br.com.springredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Aplica o filtro de autenticação necessario
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final String tokenHeader;
    private final CacheUserAuthenticationService cacheAuth;
    private final JwtTokenUtil tokenUtil;

    public AuthenticationTokenFilter(
            @Value("${springboot.security.jwt.controller.tokenHeader}") final String tokenHeader,
            final CacheUserAuthenticationService cacheAuth,
            final JwtTokenUtil tokenUtil) {
        this.cacheAuth = cacheAuth;
        this.tokenUtil = tokenUtil;
        this.tokenHeader = tokenHeader;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws ServletException, IOException {
        //recuperando o possivel token presente no cabeçalho
        final String authToken = request.getHeader(this.tokenHeader);

        if (!isNullOrEmpty(authToken)) {

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                //buscando o usuário no cache
                final UserDetails userDetails = this.cacheAuth.get(authToken);

                //verificando a validade do token
                if (tokenUtil.validateToken(authToken, userDetails)) {
                    final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        //dispachando a requisição
        chain.doFilter(request, response);
    }
}

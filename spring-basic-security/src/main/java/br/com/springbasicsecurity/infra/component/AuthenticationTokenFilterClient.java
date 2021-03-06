package br.com.springbasicsecurity.infra.component;

import br.com.springbasicsecurity.infra.component.util.JwtTokenUtil;
import br.com.springbasicsecurity.infra.service.CacheUserAuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
public class AuthenticationTokenFilterClient extends OncePerRequestFilter {

    private final String tokenHeader;
    private final CacheUserAuthenticationService cacheAuth;
    private final JwtTokenUtil tokenUtil;

    public AuthenticationTokenFilterClient(
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
                if (userDetails != null) {
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

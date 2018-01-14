package br.com.springbasicsecurity;

import br.com.springbasicsecurity.service.CacheUserAuthenticationService;
import br.com.springmodel.security.jwt.JwtUser;
import br.com.springmodel.security.jwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
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
 * @author Joel Rodrigues Moreira on 12/01/18.
 * @project spring-cloud
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil tokenUtil;
    private final String tokenHeader;
    private final CacheUserAuthenticationService cacheAuth;

    @Autowired
    public AuthenticationTokenFilter(
            final UserDetailsService userDetailsService,
            final JwtTokenUtil tokenUtil,
            @Value("${security.jwt.controller.tokenHeader}") final String tokenHeader,
            final CacheUserAuthenticationService cacheAuth) {
        this.userDetailsService = userDetailsService;
        this.tokenUtil = tokenUtil;
        this.tokenHeader = tokenHeader;
        this.cacheAuth = cacheAuth;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws ServletException, IOException {
        //recuperando o possivel token presente no cabeçalho
        final String authToken = request.getHeader(this.tokenHeader);

        if (!isNullOrEmpty(authToken)) {
            //extraindo o nome de usuário
            final String username = tokenUtil.getUsernameFromToken(authToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                //buscando o usuário presente no token
                final JwtUser userDetails = (JwtUser) this.userDetailsService.loadUserByUsername(username);

                //verificando a validade do token
                if (tokenUtil.validateToken(authToken, userDetails)) {
                    final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    this.cacheAuth.set(authToken, userDetails);
                }
            }
        }
        //dispachando a requisição
        chain.doFilter(request, response);
    }
}

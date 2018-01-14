package br.com.springbasicsecurity.controller;

import br.com.springbasicsecurity.response.JwtTokenResponse;
import br.com.springexception.throwables.security.SpringBootSecurityBadRequestException;
import br.com.springexception.throwables.security.SpringBootSecurityUserNameOrPasswordInvalidException;
import br.com.springmodel.security.jwt.JwtUser;
import br.com.springbasicsecurity.component.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * RestController responsavel por despachar novas requisições de token
 *
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
public class AuthenticationRestController {

    protected final String tokenHeader;
    protected static final String USERNAME = "username";
    protected static final String PASSWORD = "password";

    protected final AuthenticationManager authenticationManager;
    protected final JwtTokenUtil jwtTokenUtil;
    protected final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationRestController(final @Value("${springboot.security.jwt.controller.tokenHeader}") String tokenHeader, final AuthenticationManager authenticationManager, final JwtTokenUtil jwtTokenUtil, final UserDetailsService userDetailsService) {
        this.tokenHeader = tokenHeader;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "${springboot.security.jwt.controller.loginEndPoint}", method = RequestMethod.POST)
    public ResponseEntity createAuthenticationToken(@RequestBody Map<String, String> payload, Device device, HttpServletRequest request) {
        if (payload.isEmpty() || payload.size() < 2 || !payload.containsKey(USERNAME) || !payload.containsKey(PASSWORD)) {
            throw new SpringBootSecurityBadRequestException(User.class, null, "Informe os campos de usuário e senha")
                    .addDetails(USERNAME, "algum usuário válido")
                    .addDetails(PASSWORD, "uma senha válida!");
        }

        if (payload.size() > 2) {
            throw new SpringBootSecurityBadRequestException(User.class, null, "Por favor informe somente os campos de usuário e senha")
                    .addDetails(USERNAME, "algum usuário válido")
                    .addDetails(PASSWORD, "uma senha válida!");
        }

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            payload.get(USERNAME),
                            payload.get(PASSWORD)
                    )
            );

            //despachando a requisição para a validação do spring
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //se chegou até aqui é sinal que o usuário e senha é valido
            final UserDetails userDetails = userDetailsService.loadUserByUsername(payload.get(USERNAME));
            //gerando o token de autorização
            JwtTokenResponse token = new JwtTokenResponse(jwtTokenUtil.generateToken(userDetails, device));
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException ex) {
            throw new SpringBootSecurityUserNameOrPasswordInvalidException();
        }
    }

    @RequestMapping(value = "${springboot.security.jwt.controller.refreshEndPoint}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtTokenResponse(refreshedToken));
        }
        throw new SpringBootSecurityBadRequestException(null, null, "Token invalido. Faça login novamente");
    }
}

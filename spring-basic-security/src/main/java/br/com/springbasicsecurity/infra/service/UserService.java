package br.com.springbasicsecurity.infra.service;

import br.com.springbasicsecurity.infra.response.JwtTokenResponse;
import br.com.springmodel.security.jwt.JwtUser;
import br.com.springmodel.security.model.Passwd;
import br.com.springmodel.security.model.User;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
 * @author Joel Rodrigues Moreira on 12/01/18.
 * @project spring-cloud
 */
public interface UserService {
    User save(final User user);

    boolean remove(final User user);

    User update(final User user);

    User updatePasswd(final Passwd user);

    User findByEmail(final String email);

    User findById(final String id);

    Collection<User> findAll();

    Authentication getCurrentAuthentication();

    JwtUser getCurrentJwtUser();

    JwtTokenResponse getCurrentToken();

    User getCurrentUser();
}

package br.com.springgateway.security.service;

import br.com.springmodel.security.jwt.JwtUser;
import br.com.springmodel.security.model.Passwd;
import br.com.springmodel.security.model.User;
import org.springframework.security.core.Authentication;

import java.util.Collection;


/**
 * Created by joel on 17/01/17.
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

    User getCurrentUser();
}

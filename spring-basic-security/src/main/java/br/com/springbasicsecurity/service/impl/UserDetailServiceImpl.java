package br.com.springbasicsecurity.service.impl;

import br.com.springbasicsecurity.repository.UserRepository;
import br.com.springmodel.security.jwt.JwtUser;
import br.com.springmodel.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Serviço necessario por recuperar usuarios vindos do banco de dados
 *
 * @author Joel Rodrigues Moreira on 12/01/18.
 * @project spring-cloud
 */
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        } else {
            return new JwtUser(user);
        }
    }
}

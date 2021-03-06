package br.com.springconfig.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuração de segurança do ambiente
 *
 * @author Joel Rodrigues Moreira on 28/12/17.
 * @project bug
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String userName;
    private final String passWord;
    private final String role;

    public SecurityConfig(
            @Value("${security.user.name}") final String userName,
            @Value("${security.user.password}") final String passWord,
            @Value("${security.user.role}") final String role) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
    }

    /**
     * Configurando usuário e senha necessário para autenticação no serviço
     */
    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(this.userName)
                .password(this.passWord)
                .roles(this.role);
    }

    /**
     * Configurando a segurança exigida para acessar o serviço
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .hasRole(this.role)
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();
    }
}

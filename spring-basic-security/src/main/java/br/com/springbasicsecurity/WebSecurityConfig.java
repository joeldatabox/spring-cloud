package br.com.springbasicsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Joel Rodrigues Moreira on 12/01/18.
 * @project spring-cloud
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UnauthorizedHandler unauthorizedHandler;
    private final UserDetailsService userDetailsService;
    private final AuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    public WebSecurityConfig(
            final UnauthorizedHandler unauthorizedHandler,
            final UserDetailsService userDetailsService,
            final AuthenticationTokenFilter authenticationTokenFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.userDetailsService = userDetailsService;
        this.authenticationTokenFilter = authenticationTokenFilter;
    }

    /*@Bean
    public AuthenticationTokenFilter authenticationTokenFilter() {
        return new AuthenticationTokenFilter();
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    protected void configureAuthentication(AuthenticationManagerBuilder authentication) throws Exception {
        authentication
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //não é necessário CSRF pois nossos tokens evita isso
                .csrf().disable()
                //ouvinte que despacha requisiçõe não autorizadas
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .exceptionHandling().accessDeniedPage("/403").and()
                //desativando o controle de sessão
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // permite acesso a qualquer recurso estatico
                //.antMatchers(
                //        HttpMethod.GET,
                //        "/",
                //        "/*.html",
                //        "/**/*.{png,jpg,jpeg,svg.ico}",
                //        "/**/*.{html,css,js,svg,woff,woff2}",
                //        //endpoit padrão da aplicação
                //        "/login",
                //        "/create-user",
                //        "/home/**"
                //).permitAll()
                //permitindo acesso aos endpoint de login
                //.antMatchers(loginEndPoint, refreshTokenEndPoin, createEndPoint).permitAll()
                //barrando qualquer outra requisição não autenticada
                .anyRequest().authenticated();

        //adicionando o filtro de segurança
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //desabilitando controle de cache
        http.headers().cacheControl();
    }
}

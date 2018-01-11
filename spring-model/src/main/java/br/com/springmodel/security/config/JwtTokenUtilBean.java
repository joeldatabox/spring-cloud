package br.com.springmodel.security.config;

import br.com.springmodel.security.service.SecretService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Joel Rodrigues Moreira on 08/01/18.
 * @project demo
 */
@Configuration
public class JwtTokenUtilBean {
  /*  @Bean
    public JwtTokenUtil createJwtTokenUtil(@Autowired final SecretService secretService) {
        return new JwtTokenUtil(secretService);
    }*/

    @Bean
    public SecretService createSecretService() {
        return new SecretService();
    }
}

package br.com.springpessoa;

import br.com.springbasicsecurity.infra.component.util.JwtTokenUtil;
import br.com.springbasicsecurity.infra.service.CacheUserAuthenticationService;
import br.com.springbasicsecurity.infra.service.impl.CacheUserAuthenticationServiceImpl;
import br.com.springmodel.security.service.SecretService;
import br.com.springredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = "br.com")
@ComponentScan(basePackages = {
        "br.com.springpessoa",
        "br.com.springredis.service"
})
@EnableEurekaClient
@Configuration
public class SpringPessoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPessoaApplication.class, args);
    }

    //sera desnecessario deopis
    @Bean
    @Autowired
    public CacheUserAuthenticationService creaCacheUserAuthenticationService(final RedisService service) {
        return new CacheUserAuthenticationServiceImpl(service);
    }

    @Bean
    public JwtTokenUtil createJwtTokenUtil() {
        return new JwtTokenUtil(new SecretService());
    }
}

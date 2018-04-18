package br.com.springexception.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Joel Rodrigues Moreira on 18/04/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
@Configuration
public class MyBean {
    @Bean
    public MyErrorDecoder myErrorDecoder() {
        return new MyErrorDecoder();
    }
}

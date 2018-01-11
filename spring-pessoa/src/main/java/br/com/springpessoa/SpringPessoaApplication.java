package br.com.springpessoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "br.com")
@EnableEurekaClient
public class SpringPessoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPessoaApplication.class, args);
    }
}

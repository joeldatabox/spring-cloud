package br.com.springpessoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//Packages onde existem entidades
@EntityScan(basePackages = {"br.com.springmodel"})
@ComponentScan(basePackages = {
        "br.com.springpessoa",
        "br.com.springredis.service",
        "br.com.springbasicsecurity.zuul.client.service",
        "br.com.springexception.service"
})
@EnableEurekaClient
public class SpringPessoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPessoaApplication.class, args);
    }

}

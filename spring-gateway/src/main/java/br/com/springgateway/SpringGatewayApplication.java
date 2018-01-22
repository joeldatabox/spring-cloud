package br.com.springgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//Packages onde existem entidades
@EntityScan(basePackages = {"br.com.springmodel"})
//Packages onde existem componentes, serviços e configurações
@ComponentScan(basePackages = {
        //Injeções internas do projeto
        "br.com.springgateway",
        //Configurações de segurança para o gateway
        "br.com.springbasicsecurity.zuul.gateway.service",
        //Configurações do serviço de cache
        "br.com.springredis.service",
        //Configurações de exceptions
        "br.com.springexception.service",
})
@EnableZuulProxy
@EnableEurekaClient
public class SpringGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringGatewayApplication.class, args);
    }
}

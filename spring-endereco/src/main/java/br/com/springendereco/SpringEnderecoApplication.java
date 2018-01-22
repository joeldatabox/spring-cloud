package br.com.springendereco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//Packages onde existem entidades
@EntityScan(basePackages = {"br.com.springmodel"})
@ComponentScan(basePackages = {
		"br.com.springendereco",
		"br.com.springredis.service",
		"br.com.springbasicsecurity.zuul.client.service",
		"br.com.springexception.service"
})
@EnableEurekaClient
@EnableFeignClients
public class SpringEnderecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEnderecoApplication.class, args);
	}
}

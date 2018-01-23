package br.com.springendereco.config;

import br.com.springmongoconfig.service.repository.impl.CustomMongoRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Realiza a configuração do mongo db
 *
 * @author Joel Rodrigues Moreira on 10/01/18.
 * @project demo
 */
@Configuration
@EnableMongoRepositories(basePackages = {
        "br.com.springbasicsecurity.infra.repository",
        "br.com.springendereco"
}, repositoryBaseClass = CustomMongoRepositoryImpl.class)
public class MongoConfig extends br.com.springmongoconfig.service.MongoConfig {

    public MongoConfig(@Value("${spring.data.mongodb.database}") final String dataBaseName,
                       @Value("${spring.data.mongodb.host}") final String hostDataBase,
                       @Value("${spring.data.mongodb.port}") final String portDataBase,
                       @Value("${spring.data.mongodb.username}") final String userName,
                       @Value("${spring.data.mongodb.password}") final String password) {
        super(dataBaseName, hostDataBase, portDataBase, userName, password);
    }
}

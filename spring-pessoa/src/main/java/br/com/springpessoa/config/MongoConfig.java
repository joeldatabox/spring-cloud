package br.com.springpessoa.config;


import br.com.springmongoconfig.DemoMongoConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Joel Rodrigues Moreira on 10/01/18.
 * @project demo
 */
@Configuration
//@EnableMongoRepositories(basePackages = "br.com", repositoryBaseClass = InvistateRepositoryCustomImpl.class)
public class MongoConfig extends DemoMongoConfig {
    public MongoConfig(
            @Value("${spring.data.mongodb.database}") final String dataBaseName,
            @Value("${spring.data.mongodb.host}") final String hostDataBase,
            @Value("${spring.data.mongodb.port}") final String portDataBase,
            @Value("${spring.data.mongodb.username}") final String userName,
            @Value("${spring.data.mongodb.password}") final String password) {
        super(dataBaseName, hostDataBase, portDataBase, userName, password);
    }
}

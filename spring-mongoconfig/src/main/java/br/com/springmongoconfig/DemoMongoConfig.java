package br.com.springmongoconfig;

import br.com.springmongoconfig.converters.BigDecimalToDecimal128Converter;
import br.com.springmongoconfig.converters.Decimal128ToBigDecimalConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoCredential.createCredential;
import static java.util.Collections.singletonList;

/**
 * Realiza a configuração do mongo db
 *
 * @author Joel Rodrigues Moreira on 10/01/18.
 * @project demo
 */
public class DemoMongoConfig extends AbstractMongoConfiguration {
    private final CustomConversions converters;

    protected final String dataBaseName;
    protected final String hostDataBase;
    protected final String portDataBase;
    protected final String userName;
    protected final String password;

    public DemoMongoConfig(final String dataBaseName,
                           final String hostDataBase,
                           final String portDataBase,
                           final String userName,
                           final String password) {

        this.dataBaseName = dataBaseName;
        this.hostDataBase = hostDataBase;
        this.portDataBase = portDataBase;
        this.userName = userName;
        this.password = password;

        final List listConverters = new ArrayList(2);
        listConverters.add(new BigDecimalToDecimal128Converter());
        listConverters.add(new Decimal128ToBigDecimalConverter());

        this.converters = new CustomConversions(listConverters);
    }

    @Override
    protected String getDatabaseName() {
        return dataBaseName;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(
                singletonList(new ServerAddress(this.hostDataBase, Integer.valueOf(this.portDataBase))),
                singletonList(createCredential(this.userName, this.dataBaseName, password.toCharArray())));
    }

    @Override
    public CustomConversions customConversions() {
        return this.converters;
    }

    @Bean
    public MongoRepositoryFactory getMongoRepositoryFactory() {
        try {
            return new MongoRepositoryFactory(this.mongoTemplate());
        } catch (Exception e) {
            throw new RuntimeException("error creating mongo repository factory", e);
        }
    }

}

package br.com.springredis.service.config;

import br.com.springredis.service.RedisService;
import br.com.springredis.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Joel Rodrigues Moreira on 08/01/18.
 * @project demo
 */
@Configuration
public class RedisConfig {
    private final String host;
    private final int port;

    public RedisConfig(
            @Value("${springboot.redis.host:localhost}") final String host,
            @Value("${springboot.redis.port:6379}") final int port) {
        this.host = host;
        this.port = port;
    }

    @Bean
    public RedisService createService(
            @Value("${springboot.redis.prefixHash:cache}") final String prefixHash,
            @Autowired RedisTemplate redisTemplate) {
        return new RedisServiceImpl(prefixHash, redisTemplate);
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setUsePool(true);
        return factory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager cacheManager(@Autowired final RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }
}

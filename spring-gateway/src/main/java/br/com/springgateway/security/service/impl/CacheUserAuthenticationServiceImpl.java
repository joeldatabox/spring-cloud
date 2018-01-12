package br.com.springgateway.security.service.impl;

import br.com.springgateway.security.service.CacheUserAuthenticationService;
import br.com.springmodel.security.jwt.JwtUser;
import br.com.springredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Joel Rodrigues Moreira on 09/01/18.
 * @project demo
 */
@Service
public class CacheUserAuthenticationServiceImpl implements CacheUserAuthenticationService {

    private final RedisService redisService;

    @Autowired
    public CacheUserAuthenticationServiceImpl(final RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void set(final String token, final JwtUser user) {
        this.redisService.set(token, user, 30000);
    }

    @Override
    public JwtUser get(final String token) {
        return (JwtUser) redisService.get(token);
    }
}

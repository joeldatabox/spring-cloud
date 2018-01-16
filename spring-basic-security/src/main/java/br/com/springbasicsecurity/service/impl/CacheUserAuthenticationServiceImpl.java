package br.com.springbasicsecurity.service.impl;

import br.com.springbasicsecurity.service.CacheUserAuthenticationService;
import br.com.springmodel.security.jwt.JwtUser;
import br.com.springmodel.security.model.User;
import br.com.springredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        final Map<String, Object> mapJwtUser = new HashMap<>(7);
        mapJwtUser.put("id", user.getId());
        mapJwtUser.put("email", user.getEmail());
        mapJwtUser.put("password", user.getPassword());
        mapJwtUser.put("authorities", user.getAuthorities());
        mapJwtUser.put("lastPasswordResetDate", user.getLastPasswordResetDate());
        mapJwtUser.put("originUser", user.getOriginUser());
        mapJwtUser.put("username", user.getUsername());
        this.redisService.set(token, mapJwtUser, 30000);
    }

    @Override
    public JwtUser get(final String token) {
        final Map<String, Object> mapJwtUser = (Map<String, Object>) redisService.get(token);
        return new JwtUser.UserBuilder()
                .setId((String) mapJwtUser.get("id"))
                .setEmail((String) mapJwtUser.get("email"))
                .setPassword((String) mapJwtUser.get("password"))
                .setAuthorities((Collection<? extends GrantedAuthority>) mapJwtUser.get("authorities"))
                .setLastPasswordResetDate((Date) mapJwtUser.get("lastPasswordResetDate"))
                .setOriginUser((User) mapJwtUser.get("originUser"))
                .setName((String) mapJwtUser.get("username"))
                .build();
    }
}

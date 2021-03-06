package br.com.springbasicsecurity.infra.service.impl;

import br.com.springbasicsecurity.infra.service.CacheUserAuthenticationService;
import br.com.springmodel.security.jwt.JwtUser;
import br.com.springredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joel Rodrigues Moreira on 09/01/18.
 * @project demo
 */
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
        this.redisService.set(token, mapJwtUser, 3600000);
    }

    @Override
    public JwtUser get(final String token) {
        final Map<String, Object> mapJwtUser = (Map<String, Object>) redisService.get(token);
        if (mapJwtUser != null) {
            return new JwtUser.UserBuilder()
                    .setId((String) mapJwtUser.get("id"))
                    .setEmail((String) mapJwtUser.get("email"))
                    .setPassword((String) mapJwtUser.get("password"))
                    .setAuthorities((Collection<? extends GrantedAuthority>) mapJwtUser.get("authorities"))
                    .setLastPasswordResetDate(mapJwtUser.get("lastPasswordResetDate") != null ? new Date(Long.valueOf(mapJwtUser.get("lastPasswordResetDate").toString())) : null)
                    //.setOriginUser((User) mapJwtUser.get("originUser"))
                    .setName((String) mapJwtUser.get("username"))
                    .build();
        }
        return null;
    }
}

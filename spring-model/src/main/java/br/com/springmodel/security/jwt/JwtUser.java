package br.com.springmodel.security.jwt;

import br.com.springmodel.security.model.Authority;
import br.com.springmodel.security.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Joel Rodrigues Moreira on 08/01/18.
 * @project demo
 */
public class JwtUser implements UserDetails {

    private final String id;
    private final String name;

    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final Date lastPasswordResetDate;
    /*@Value("${springboot..security.jwt.issuer}")
    private String issuer;*/
    private final User originUser;

    public JwtUser(final User user) {
        this.id = user.getId();
        this.name = user.getNome();
        this.password = user.getPasswd();
        this.email = user.getEmail();
        this.authorities = mapToGrantedAuthorities(user.getAuthorities());
        this.enabled = user.isEnable();
        this.lastPasswordResetDate = user.getLastPasswordResetDate();
        this.originUser = user;
    }

    public JwtUser(final UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.name = userBuilder.name;
        this.password = userBuilder.password;
        this.email = userBuilder.email;
        this.authorities = userBuilder.authorities;
        this.enabled = userBuilder.enabled;
        this.lastPasswordResetDate = userBuilder.lastPasswordResetDate;
        this.originUser = userBuilder.originUser;
    }

    @JsonIgnore
    public User getOriginUser() {
        return originUser;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    private static final List<GrantedAuthority> mapToGrantedAuthorities(final Collection<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }

    public static class UserBuilder {
        private String id;
        private String name;

        private String password;
        private String email;
        private Collection<? extends GrantedAuthority> authorities;
        private boolean enabled;
        private Date lastPasswordResetDate;
        /*@Value("${springboot..security.jwt.issuer}")
        private String issuer;*/
        private User originUser;

        public UserBuilder setId(final String id) {
            this.id = id;
            return this;
        }

        public UserBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setAuthorities(final Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserBuilder setEnabled(final boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserBuilder setLastPasswordResetDate(final Date lastPasswordResetDate) {
            this.lastPasswordResetDate = lastPasswordResetDate;
            return this;
        }

        public UserBuilder setOriginUser(final User originUser) {
            this.originUser = originUser;
            return this;
        }

        public JwtUser build() {
            return new JwtUser(this);
        }
    }
}
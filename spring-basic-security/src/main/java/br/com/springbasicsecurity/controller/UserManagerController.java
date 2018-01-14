package br.com.springbasicsecurity.controller;

import br.com.springbasicsecurity.service.UserService;
import br.com.springmodel.security.jwt.JwtUser;
import br.com.springmodel.security.model.Passwd;
import br.com.springmodel.security.model.User;
import br.com.springmodel.security.model.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
@RequestMapping(value = "${security.jwt.controller.managerUserEndPoint}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class UserManagerController {

    private UserService service;

    @Autowired
    public UserManagerController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity update(@RequestBody User user) {
        user.setId(getCurrentUser().getId());
        User other = service.update(user);
        return ResponseEntity.ok(other);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserResource get() {
        return new UserResource(getCurrentUser());
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updatePasswd(@RequestBody @Valid Passwd passwd) {
        passwd.setId(getCurrentUser().getId());
        return ResponseEntity.ok().body(service.updatePasswd(passwd));
    }

    private Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    private JwtUser getCurrentJwtUser() {
        return (JwtUser) getCurrentAuthentication().getPrincipal();
    }

    private User getCurrentUser() {
        return getCurrentJwtUser().getOriginUser();
    }
}

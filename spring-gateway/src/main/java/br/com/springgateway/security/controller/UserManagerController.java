package br.com.springgateway.security.controller;


import br.com.springbasicsecurity.infra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joel Rodrigues Moreira on 14/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
@RestController
public class UserManagerController extends br.com.springbasicsecurity.infra.controller.UserManagerController {

    @Autowired
    public UserManagerController(UserService service) {
        super(service);
    }

}

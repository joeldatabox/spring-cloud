package br.com.springgateway.security.controller;


import br.com.springbasicsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagerController extends br.com.springbasicsecurity.controller.UserManagerController {

    @Autowired
    public UserManagerController(UserService service) {
        super(service);
    }

}

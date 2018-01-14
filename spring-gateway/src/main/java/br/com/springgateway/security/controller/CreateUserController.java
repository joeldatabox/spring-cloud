package br.com.springgateway.security.controller;

import br.com.springbasicsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joel on 26/03/17.
 */
@RestController
public class CreateUserController extends br.com.springbasicsecurity.controller.CreateUserController {
    @Autowired
    public CreateUserController(final ApplicationEventPublisher eventPublisher, final UserService service) {
        super(eventPublisher, service);
    }
}

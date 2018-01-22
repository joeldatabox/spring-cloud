package br.com.springpessoa.controller;

import br.com.springmodel.model.Pessoa;
import br.com.springpessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Joel Rodrigues Moreira on 10/01/18.
 * @project demo
 */
@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    private final PessoaService service;
    private final EnderecoClient enderecoClient;

    @Autowired
    public PessoaController(final PessoaService service, final EnderecoClient enderecoClient) {
        this.service = service;
        this.enderecoClient = enderecoClient;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa save(@Valid @RequestBody Pessoa model, HttpServletResponse response) {
        final Pessoa p = service.save(model);
        response.addHeader("Location", "http://localhost:8083/demo-pessoaservice/api/pessoas/" + p.getId());
        return p;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Pessoa model) {
        model.setId(id);
        return ResponseEntity.ok(service.update(model));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll(@RequestParam Map<String, Object> allRequestParams, HttpServletResponse response) {
        System.out.println("findall");
        response.addHeader("testando", "TEste");
        return ResponseEntity.ok(
                service.findAll(allRequestParams)
                        .stream()
                        .map(p -> p.setEndereco(this.enderecoClient.getEnderecoById(p.getId())))
                        .collect(Collectors.toList())
        );
    }
}

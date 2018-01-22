package br.com.springendereco.controller;

import br.com.springmodel.model.Endereco;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Joel Rodrigues Moreira on 22/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findAll(@PathVariable("id") String id, @RequestParam Map<String, Object> allRequestParams, HttpServletResponse response) {
        return ResponseEntity.ok(new Endereco(id, "Endereco " + id));
    }
}

package br.com.springpessoa.controller;

import br.com.springmodel.model.Endereco;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Joel Rodrigues Moreira on 22/01/18.
 * e-mail: <a href="mailto:joel.databox@gmail.com">joel.databox@gmail.com</a>
 * @project spring-cloud
 */
@FeignClient("demo-enderecoservice")
public interface EnderecoClient {
    @RequestMapping(value = "/api/enderecos/{id}", method = RequestMethod.GET)
    public Endereco getEnderecoById(@PathVariable("id") String id);
}

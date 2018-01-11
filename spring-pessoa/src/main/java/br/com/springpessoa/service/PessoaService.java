package br.com.springpessoa.service;

import br.com.springmodel.model.Pessoa;

import java.util.List;
import java.util.Map;

/**
 * @author Joel Rodrigues Moreira on 10/01/18.
 * @project demo
 */
public interface PessoaService {
    Pessoa save(final Pessoa value);

    Pessoa update(final Pessoa value);

    Pessoa findById(final String id);

    void deletById(final String sync);

    Long count(final Map<String, Object> allRequestParams);

    List<Pessoa> findAll(final Map<String, Object> allRequestParams);
}

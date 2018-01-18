package br.com.springpessoa.service.impl;

import br.com.springmodel.model.Pessoa;
import br.com.springpessoa.repository.PessoaRepository;
import br.com.springpessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Joel Rodrigues Moreira on 10/01/18.
 * @project demo
 */
@Service
public class PessoaServiceImpl implements PessoaService {
    private final PessoaRepository repository;

    @Autowired
    public PessoaServiceImpl(final PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pessoa save(final Pessoa value) {
        return repository.save(value);
    }

    @Override
    public Pessoa update(final Pessoa value) {
        return repository.save(value);
    }

    @Override
    public Pessoa findById(final String id) {
        return repository.findOne(id);
    }

    @Override
    public void deletById(final String id) {
        this.repository.delete(id);
    }

    @Override
    public Long count(final Map<String, Object> allRequestParams) {
        return repository.count();
    }

    @Override
    public List<Pessoa> findAll(final Map<String, Object> allRequestParams) {
        return this.repository.findAll();
    }
}

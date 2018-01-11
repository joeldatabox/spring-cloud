package br.com.springpessoa.repository;

import br.com.springmodel.model.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by master on 07/04/17.
 */
public interface PessoaRepository extends MongoRepository<Pessoa, String> {
}

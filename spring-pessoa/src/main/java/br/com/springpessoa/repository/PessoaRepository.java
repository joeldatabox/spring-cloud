package br.com.springpessoa.repository;

import br.com.springmodel.model.Pessoa;
import org.springframework.stereotype.Repository;

/**
 * Created by master on 07/04/17.
 */
@Repository
public interface PessoaRepository extends CustomMongoRepository<Pessoa, String> {
}

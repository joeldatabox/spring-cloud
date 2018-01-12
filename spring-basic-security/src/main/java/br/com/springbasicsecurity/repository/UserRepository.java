package br.com.springbasicsecurity.repository;

import br.com.springmodel.security.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Joel Rodrigues Moreira on 12/01/18.
 * @project spring-cloud
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}


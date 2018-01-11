package br.com.springgateway.security.repository;

import br.com.springmodel.security.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by joel on 16/01/17.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}


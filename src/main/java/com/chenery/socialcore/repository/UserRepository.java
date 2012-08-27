package com.chenery.socialcore.repository;

import com.chenery.socialcore.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 *
 */
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ firstName: ?0 }")
    List<User> findByFirstName(String firstName);
}

package com.chenery.socialcore.repository.impl;

import com.chenery.socialcore.model.User;
import com.chenery.socialcore.repository.CustomUserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Repository(value = "customUserRepository")
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Inject
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> findUsersWithNumberOfStatusUpdates(int numberOfUpdates) {

        Query query = new Query(Criteria.where("statusUpdates").size(numberOfUpdates));

        return mongoTemplate.find(query, User.class);
    }
}

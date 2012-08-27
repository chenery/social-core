package com.chenery.socialcore.repository;

import com.chenery.socialcore.model.User;

import java.util.List;

/**
 *
 */
public interface CustomUserRepository {

    List<User> findUsersWithNumberOfStatusUpdates(int numberOfUpdates);
}

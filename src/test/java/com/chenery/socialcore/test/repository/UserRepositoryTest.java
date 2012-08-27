package com.chenery.socialcore.test.repository;

import com.chenery.socialcore.model.StatusUpdate;
import com.chenery.socialcore.model.User;
import com.chenery.socialcore.repository.CustomUserRepository;
import com.chenery.socialcore.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class UserRepositoryTest {

    @Inject
    private CustomUserRepository customUserRepository;

    @Inject
    private UserRepository userRepository;

    @Before
    public void initialiseTests() {

        // clean down all the users in the test database
        userRepository.deleteAll();
    }

    @Test
    public void testSaveGet() {

        User user = saveUser("paulchenery@gmail.com", "Paul", "Chenery");

        assertNotNull("User id should be non-null after save", user.getId());

        User check = userRepository.findOne(user.getId());

        assertEquals("User not found via findOne()", user, check);
    }

    @Test
    public void testFindByFirstName() {

        saveUser("paulchenery1@gmail.com", "Paul", "Chenery1");
        saveUser("paulchenery2@gmail.com", "Paul1", "Chenery2");
        saveUser("paulchenery3@gmail.com", "Paul", "Chenery3");

        List<User> users = userRepository.findByFirstName("Paul");

        assertEquals("Incorrect number of results", 2, users.size());
    }

    @Test
    public void testAddFriend() {

        User paul = saveUser("paulchenery@gmail.com", "Paul", "Chenery");
        User ben = saveUser("ben@email.com", "Ben", "Smith");

        // This is a traditional many to many relationship mapping
        paul.getFriends().add(ben);

        userRepository.save(paul);

        User checkPaul = userRepository.findOne(paul.getId());

        Set<User> checkFriends = checkPaul.getFriends();

        // there appears to be an implicit fetch of the dbref user object...
        assertTrue("Paul's only friend should be Ben", checkFriends.size() == 1 && checkFriends.contains(ben));
    }

    @Test
    public void testAddStatusUpdate() {

        User paul = saveUser("paulchenery@gmail.com", "Paul", "Chenery");
        addStatusUpdateToUser(paul, "Off to shops");
        addStatusUpdateToUser(paul, "Time for bed");

        // todo work out how you can publish status updates to your friends

        assertEquals("Paul's status updates not persisted", 2,
                userRepository.findOne(paul.getId()).getStatusUpdates().size());
    }

    @Test
    public void testGetUsersByNumberOfUpdates () {

        // create one user with one status updates and one user with 3
        User paul = saveUser("paulchenery@gmail.com", "Paul", "Chenery");
        addStatusUpdateToUser(paul, "Off to the shops");

        User ben = saveUser("ben@email.com", "Ben", "Smith");
        addStatusUpdateToUser(ben, "Watching TV");
        addStatusUpdateToUser(ben, "Eating pasta");
        addStatusUpdateToUser(ben, "Playing football");

        List<User> users = customUserRepository.findUsersWithNumberOfStatusUpdates(3);

        assertTrue("Incorrect number of users found", users != null && users.size() == 1);

        assertEquals("Wrong user found", ben, users.get(0));
    }

    private User saveUser(String email, String firstName, String lastName) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userRepository.save(user);
        return user;
    }

    private void addStatusUpdateToUser(User user, String comment) {

        StatusUpdate statusUpdate = new StatusUpdate();
        statusUpdate.setComment(comment);
        statusUpdate.setUpdateTime(new Date());

        user.getStatusUpdates().add(statusUpdate);
        userRepository.save(user);
    }

}

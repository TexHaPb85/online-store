package edu.hillel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hillel.entities.User;
import edu.hillel.repository.user.UserRepository;


import java.util.Objects;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static UserService singletonUser;
    public static User loggedInUser;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static UserService getSingletonInstance(UserRepository userRepository) {
        if (singletonUser == null) {
            singletonUser = new UserService(userRepository);
        }
        return singletonUser;
    }

    public User logIn(String login, String password) {
        loggedInUser = userRepository
                .findUserByLoginPassword(login, password)
                .orElseThrow(() -> new IllegalArgumentException("There no user with such login and password"));
        return loggedInUser;
    }

    public void logOut() {
        loggedInUser = null;
    }

    public Set<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void addUser(User user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("User is null");
        }

        if (!getAllUsers().contains(user)) {
            userRepository.addUser(user);
        } else {
            System.out.println("Cannot add this user. User already exist");
        }
    }

    public void removeUser(String login) {
        userRepository.removeUser(login);
    }

    public void updateUser(String login, User updatedUser) {
        if (Objects.isNull(updatedUser)) {
            throw new IllegalArgumentException("Updated user is null");
        }
        if (getAllUsers().contains(updatedUser)) {
            System.out.println("Cannot update to this user. User already exist");
        } else {
            userRepository.updateUser(login, updatedUser);
        }
    }
}
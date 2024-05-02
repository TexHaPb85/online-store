package edu.hillel.service;

import edu.hillel.entities.User;
import edu.hillel.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;
    public static User loggedInUser;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User logIn(String login, String password) {
        loggedInUser = userRepository
            .findUserByLoginPassword(login, password)
            .orElseThrow(() -> new IllegalArgumentException("There no user with such login and password"));

        return loggedInUser;
    }
}

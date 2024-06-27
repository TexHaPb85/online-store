package edu.hillel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hillel.entities.User;
import edu.hillel.repository.user.UserRepository;

@Service
public class UserService {
    @Autowired
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

    public void logOut() {
        loggedInUser = null;
    }
}

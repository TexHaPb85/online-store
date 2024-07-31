package edu.hillel.service;

import edu.hillel.entities.User;
import edu.hillel.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.rmi.AccessException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static UserService singletonUser;
    public Map<String, HttpSession> loggedInUsers;
    public static User loggedInUser;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        loggedInUsers = new HashMap<>();
    }

    public static UserService getSingletonInstance(UserRepository userRepository) {
        if (singletonUser == null) {
            singletonUser = new UserService(userRepository);
        }
        return singletonUser;
    }

    public User logIn(String login, String password) {
        User validUser = userRepository
                .findUserByLoginPassword(login, password)
                .orElseThrow(() -> new IllegalArgumentException("There no user with such login and password"));
        loggedInUser = validUser; // for old realisation with one user
        return validUser;
    }

    public void checkAlreadyLoggedUser(Map<String, HttpSession> loggedInUsers, String userLogin) throws AccessException {
        HttpSession httpSession = loggedInUsers.get(userLogin);
        if (httpSession != null) {
            try {
                httpSession.getAttribute("user");
            } catch (IllegalStateException e) {
                return;
            }
            throw new AccessException("User already logged");
        }
    }

    public void logOut() {
        loggedInUser = null;
    }

    public void logOut(String userLogin) {
        loggedInUsers.get(userLogin).invalidate();
        loggedInUsers.remove(userLogin);
    }

    public Set<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void addUser(User user) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("User is null");
        }
        userRepository.addUser(user);
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
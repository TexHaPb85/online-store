package edu.hillel.repository.user;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import edu.hillel.entities.User;

public interface UserRepository {
    Optional<User> findUserByLoginPassword(String login, String password);

    Set<User> getAllUsers();

    void addUser(User user);

    void removeUser(String login);

    void updateUser(String login, User updatedUser);

}

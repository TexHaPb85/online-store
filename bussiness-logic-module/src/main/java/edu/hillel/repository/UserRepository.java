package edu.hillel.repository;

import java.util.List;
import java.util.Optional;

import edu.hillel.entities.User;

public interface UserRepository {
    Optional<User> findUserByLoginPassword(String login, String password);

    List<User> getAllUsers();
}

package edu.hillel.repository;

import java.util.List;
import java.util.Optional;

import edu.hillel.entities.User;

public class UserRepositoryInMemoryImpl implements UserRepository {
    private List<User> users;

    public UserRepositoryInMemoryImpl() {
        User simpleUser = User.builder()
            .login("1")
            .password("1")
            .name("simpleUser")
            .role(User.Role.CLIENT)
            .build();
        this.users = List.of(simpleUser);
    }

    @Override
    public List<User> getAllUsers() {
        return this.users;
    }

    @Override
    public Optional<User> findUserByLoginPassword(String login, String password) {
        return getAllUsers()
            .stream()
            .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
            .findAny();
    }
}

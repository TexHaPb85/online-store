package edu.hillel.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import edu.hillel.entities.User;

@Repository
public class UserRepositoryInMemoryImpl implements UserRepository {
    private List<User> users;

    public UserRepositoryInMemoryImpl() {
        User simpleUser = User.builder()
            .login("1")
            .password("1")
            .name("simpleUser")
            .role(User.Role.CLIENT)
            .build();

        User simpleUser2 = User.builder()
            .login("2")
            .password("2")
            .name("simpleUser2")
            .role(User.Role.CLIENT)
            .build();
        this.users = List.of(simpleUser, simpleUser2);
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

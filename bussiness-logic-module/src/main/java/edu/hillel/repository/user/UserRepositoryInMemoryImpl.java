package edu.hillel.repository.user;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import edu.hillel.entities.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepositoryInMemoryImpl implements UserRepository {
    private Set<User> users;
    private static UserRepositoryInMemoryImpl singletonUser;

    public static UserRepositoryInMemoryImpl getSingletonInstance() {
        if (singletonUser == null) {
            singletonUser = new UserRepositoryInMemoryImpl();
        }
        return singletonUser;
    }

    private UserRepositoryInMemoryImpl() {
        users = new HashSet<>();
        initializeClient();
        initializeAdmin();
    }

    private void initializeClient() {
        Faker faker = new Faker();
        for (int i = 1; i <= 10; i++) {
            Name name = faker.name();
            User simpleUser = User.builder()
                    .login("c" + i)
                    .password("c" + i)
                    .name("client " + name.name())
                    .role(User.Role.CLIENT)
                    .build();
            users.add(simpleUser);
        }
    }

    private void initializeAdmin() {
        Faker faker = new Faker();
        for (int i = 1; i <= 3; i++) {
            Name name = faker.name();
            User simpleUser = User.builder()
                    .login("a" + i)
                    .password("a" + i)
                    .name("admin " + name.name())
                    .role(User.Role.ADMIN)
                    .build();
            users.add(simpleUser);
        }

    }

    @Override
    public Set<User> getAllUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        } else {
            throw new IllegalArgumentException("User already exist");
        }
    }

    @Override
    public void removeUser(String login) {
        this.users.removeIf(user -> user.getLogin().equals(login));
    }

    @Override
    public void updateUser(String login, User updatedUser) {
        users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny()
                .ifPresentOrElse(user -> {
                            users.remove(user);
                            users.add(updatedUser);
                        },
                        () -> new NoSuchElementException("User is not exist"));
    }

    @Override
    public Optional<User> findUserByLoginPassword(String login, String password) {
        return getAllUsers()
                .stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findAny();
    }
}

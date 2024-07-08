package edu.hillel;

import edu.hillel.entities.User;
import edu.hillel.repository.user.UserRepository;
import edu.hillel.repository.user.UserRepositoryTxtFilesStorageImpl;
import edu.hillel.service.UserService;

public class TestUser {
    public static void main(String[] args) {

        UserRepository userRepository = UserRepositoryTxtFilesStorageImpl.getSingletonInstance();
        UserService userService = UserService.getSingletonInstance(userRepository);

        User simpleUser = User.builder()
                .login("c" + 98)
                .password("c" + 98)
                .name("client John")
                .role(User.Role.CLIENT)
                .build();

        userService.addUser(simpleUser);
        userService.removeUser("c3");
        userService.updateUser("c4", simpleUser);
    }
}

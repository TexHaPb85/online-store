package edu.hillel;

import edu.hillel.entities.User;
import edu.hillel.repository.user.UserRepository;
import edu.hillel.repository.user.UserRepositorySQLImpl;
import edu.hillel.service.UserService;

public class TestSQLUser {
    public static void main(String[] args) {

        UserRepository userRepository = UserRepositorySQLImpl.getSingletonInstance();
        UserService userService = UserService.getSingletonInstance(userRepository);

//        userService.getAllUsers().forEach(user -> System.out.println(user));

        User findUser = userService.logIn("client2", "password123");
        System.out.println(findUser);

        User newAdmin = User.builder()
                .login("NewAdmin")
                .password("1234")
                .name("admin")
                .role(User.Role.ADMIN)
                .build();
        userService.addUser(newAdmin);
//        userService.removeUser("NewAdmin");
        User updatedAdmin = User.builder()
                .login("UpdatedAdmin")
                .password("4321")
                .name("admin")
                .role(User.Role.CLIENT)
                .build();
        userService.updateUser("NewAdmin", updatedAdmin);

        userService.getAllUsers().forEach(user -> System.out.println(user));
    }
}

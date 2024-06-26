package edu.hillel;

import edu.hillel.entities.User;
import edu.hillel.repository.user.UserRepository;
import edu.hillel.repository.user.UserRepositoryTxtFilesStorageImpl;
import edu.hillel.service.UserService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        ItemRepository itemRepository = ItemRepositoryTxtFilesStorageImpl.getSingeltonInstance();
//        ItemRepositoryTxtFilesStorageImpl itemRepositoryTxtFilesStorage = new ItemRepositoryTxtFilesStorageImpl();
//        itemRepositoryTxtFilesStorage.getAllItems();
//        ItemService itemService = ItemService.getSingletonInstance(itemRepository);
//        itemService.getAllItems().forEach(item -> System.out.println(item));

        UserRepository userRepository = UserRepositoryTxtFilesStorageImpl.getSingletonInstance();
        UserService userService = UserService.getSingletonInstance(userRepository);

        User simpleUser = User.builder()
                .login("c" + 99)
                .password("c" + 99)
                .name("client John")
                .role(User.Role.CLIENT)
                .build();

        userService.addUser(simpleUser);
        userService.removeUser("c3");
        userService.updateUser("c4", simpleUser);
    }
}

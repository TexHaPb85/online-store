package edu.hillel;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.hillel.repository.item.ItemRepository;
import edu.hillel.repository.item.ItemRepositoryInMemoryImpl;
import edu.hillel.repository.item.ItemRepositorySqlStorageImpl;
import edu.hillel.repository.item.ItemRepositoryTxtFilesStorageImpl;
import edu.hillel.service.ItemService;
import edu.hillel.service.UserService;
import edu.hillel.entities.User;
import edu.hillel.repository.user.UserRepository;
import edu.hillel.repository.user.UserRepositoryTxtFilesStorageImpl;
import edu.hillel.service.UserService;

/**
 * Hello world!
 *
 */

@Configuration
@ComponentScan(basePackages = "edu.hillel")
public class App
{
    public static void main( String[] args )
    {
////        ItemRepository ir = new ItemRepositorySqlStorageImpl();
////        ItemService is = new ItemService(ir);
//        // Створення контексту Spring і сканування пакетів
//        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
//
//        // Отримання біна з контексту
//        ItemService itemService = context.getBean(ItemService.class);
//        UserService userService = context.getBean(UserService.class);
//
//        // Виклик методу біна
//        System.out.println(itemService.getAllItems());

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

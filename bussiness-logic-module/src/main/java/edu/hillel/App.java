package edu.hillel;

import edu.hillel.entities.Category;
import edu.hillel.entities.User;
import edu.hillel.repository.category.CategoryRepository;
import edu.hillel.repository.category.CategoryRepositorySQLImpl;
import edu.hillel.repository.category.CategoryRepositoryTxtFilesStorageImpl;
import edu.hillel.repository.user.UserRepository;
import edu.hillel.repository.user.UserRepositorySQLImpl;
import edu.hillel.service.CategoryService;

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

/**
 * Hello world!
 */

@Configuration
@ComponentScan(basePackages = "edu.hillel")
public class App {
    public static void main(String[] args) {
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


//        UserRepository userRepository = UserRepositorySQLImpl.getSingletonInstance();
//        UserService userService = UserService.getSingletonInstance(userRepository);
//
////        userService.getAllUsers().forEach(user -> System.out.println(user));
//
//        User findUser = userService.logIn("client2", "password123");
//        System.out.println(findUser);
//
//        User newAdmin = User.builder()
//                .login("NewAdmin")
//                .password("1234")
//                .name("admin")
//                .role(User.Role.ADMIN)
//                .build();
//        userService.addUser(newAdmin);
////        userService.removeUser("NewAdmin");
//        User updatedAdmin = User.builder()
//                .login("UpdatedAdmin")
//                .password("4321")
//                .name("admin")
//                .role(User.Role.CLIENT)
//                .build();
//        userService.updateUser("NewAdmin",updatedAdmin);
//
//        userService.getAllUsers().forEach(user -> System.out.println(user));


        CategoryRepository categoryRepository = CategoryRepositorySQLImpl.getSingletonInstance();
        CategoryService categoryService = CategoryService.getSingletonInstance(categoryRepository);

        categoryService.getAllCategory().forEach(category -> System.out.println(category.toString()));
        Category car = new Category.CategoryBuilder().name("Car").build();
        categoryService.addCategory(car);

        categoryService.getAllCategory().forEach(category -> System.out.println(category.toString()));

    }
}

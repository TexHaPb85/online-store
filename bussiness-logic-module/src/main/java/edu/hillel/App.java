package edu.hillel;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
    }
}

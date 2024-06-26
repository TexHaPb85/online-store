package edu.hillel;

import java.util.Scanner;

import edu.hillel.repository.item.ItemRepository;
import edu.hillel.repository.item.ItemRepositoryInMemoryImpl;
import edu.hillel.repository.user.UserRepository;
import edu.hillel.repository.user.UserRepositoryInMemoryImpl;
import edu.hillel.service.CartService;
import edu.hillel.service.ItemService;
import edu.hillel.service.UserService;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static UserRepository userRepository = UserRepositoryInMemoryImpl.getSingletonInstance();
    static UserService userService = UserService.getSingletonInstance(userRepository);
    static ItemRepository itemRepository = ItemRepositoryInMemoryImpl.getSingeltonInstance();
    static ItemService itemService = ItemService.getSingletonInstance(itemRepository);
    static CartService cartService = new CartService(itemRepository);

    public static void main(String[] args) {

        while (true) {
            if (UserService.loggedInUser == null) {
                System.out.println("Please enter your email:");
                String email = scanner.nextLine();
                System.out.println("Please enter your password:");
                String password = scanner.nextLine();
                userService.logIn(email, password);
            } else {
                System.out.println(
                    "Choose option:\n" +
                        "your cart: " + cartService.getCartContent() + " total amount: " + cartService.getTotalCartAmount());
                System.out.println(
                    "1. add item to the cart\n" +
                        "2. make order for selected items\n" +
                        "3. log out");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        addItemToCart();
                        break;
                    case 2:
                        break;
                    case 3:
                        userService.logOut();
                        break;
                }
            }
        }
    }

    private static void addItemToCart() {
        StringBuilder items = new StringBuilder("Enter id of item you want to buy:");
        itemService.getAllItems().forEach(item -> items.append(item.toString()).append("\n"));
        System.out.println(items);
        Long id = scanner.nextLong();
        System.out.println("Enter how much do you want to buy");
        Integer numberOfItems = scanner.nextInt();
        cartService.addItemToCart(id, numberOfItems);
    }
}

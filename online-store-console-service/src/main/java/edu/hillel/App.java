package edu.hillel;

import java.util.Scanner;

import edu.hillel.repository.cart.CartRepository;
import edu.hillel.repository.cart.CartRepositoryInMemoryImpl;
import edu.hillel.repository.cart.CartRepositoryInTxtImpl;
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
    static CartRepository cartRepository = CartRepositoryInTxtImpl.getSingletonInstance();
    static CartService cartService = new CartService(itemRepository, cartRepository);

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
                    "[Choose option]\n" +
                    "Your cart:\n" + cartService.getCartContent() +
                    "Total amount: " + cartService.getTotalCartAmount());
                System.out.println(
                    "1. add item to the cart\n" +
                    "2. remove all items by ID from the cart\n" +
                    "3. update count of items by ID\n" +
                    "4. make order for selected items\n" +
                    "5. log out");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        addItemToCart();
                        break;
                    case 2:
                        removeAllItemsByIdFromCart();
                        break;
                    case 3:
                        updateItemsCountById();
                        break;
                    case 4:
                        break;
                    case 5:
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

    private static void removeAllItemsByIdFromCart(){
        System.out.println("Enter id of items you want to remove:");
        Long id = scanner.nextLong();
        cartService.removeAllItemsById(id);
    }

    private static void updateItemsCountById(){
        System.out.println("Enter id of items you want to change:");
        Long id = scanner.nextLong();
        System.out.println("Enter new count of item:");
        int numberOfItems = scanner.nextInt();
        cartService.updateItemsCountById(id, numberOfItems);
    }
}

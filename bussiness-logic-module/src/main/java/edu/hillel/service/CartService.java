package edu.hillel.service;

import edu.hillel.entities.Item;
import edu.hillel.repository.cart.CartRepository;
import edu.hillel.repository.item.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {
    private ItemRepository itemRepository;
    private CartRepository cartRepository;
    private UserService userService;


    public CartService(ItemRepository itemRepository, CartRepository cartRepository, UserService userService) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    public void addItemToCart(Long itemId, Integer numberOfItems) {
        cartRepository.addItemToCart(userService.loggedInUser.getLogin(), itemId, numberOfItems);
    }

    public String getCartContent() {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Long, Integer> addedItems = cartRepository.getAddedItems(userService.loggedInUser.getLogin());
        for (Map.Entry<Long, Integer> entry : addedItems.entrySet()) {
            Item itemById = itemRepository.getItemById(entry.getKey());
            stringBuilder
                    .append("  [ID:")
                    .append(entry.getKey())
                    .append("] ")
                    .append(itemById.getItemName())
                    .append(" (")
                    .append(entry.getValue())
                    .append(")\n");
        }
        return stringBuilder.toString();
    }

    public Double getTotalCartAmount() {
        Double totalAmount = 0.0;
        Map<Long, Integer> addedItems = cartRepository.getAddedItems(userService.loggedInUser.getLogin());
        for (Map.Entry<Long, Integer> entry : addedItems.entrySet()) {
            Item itemById = itemRepository.getItemById(entry.getKey());
            totalAmount += itemById.getPrice() * entry.getValue();
        }

        return totalAmount;
    }

    public void removeAllItemsById(Long id) {
        try {
            cartRepository.removeAllItemsById(userService.loggedInUser.getLogin(), id);
        } catch (IllegalArgumentException e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }

    public void updateItemsCountById(Long id, Integer numberOfItems) {
        try {
            cartRepository.updateItemsCountById(userService.loggedInUser.getLogin(), id, numberOfItems);
        } catch (IllegalArgumentException e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }

}

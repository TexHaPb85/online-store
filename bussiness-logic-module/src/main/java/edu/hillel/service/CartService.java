package edu.hillel.service;

import java.util.Map;
import edu.hillel.repository.cart.CartRepository;
import org.springframework.stereotype.Service;
import edu.hillel.entities.Item;
import edu.hillel.repository.item.ItemRepository;

@Service
public class CartService {
    private ItemRepository itemRepository;
    private CartRepository cartRepository;


    public CartService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public void addItemToCart(Long itemId, Integer numberOfItems) {
        cartRepository.addItemToCart(UserService.loggedInUser.getLogin(), itemId, numberOfItems);
    }

    public String getCartContent() {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Long, Integer> addedItems = cartRepository.getAddedItems(UserService.loggedInUser.getLogin());
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
        Map<Long, Integer> addedItems = cartRepository.getAddedItems(UserService.loggedInUser.getLogin());
        for (Map.Entry<Long, Integer> entry : addedItems.entrySet()) {
            Item itemById = itemRepository.getItemById(entry.getKey());
            totalAmount += itemById.getPrice() * entry.getValue();
        }

        return totalAmount;
    }

    public void removeAllItemsById(Long id){
        try {
            cartRepository.removeAllItemsById(UserService.loggedInUser.getLogin(), id);
        }
        catch (IllegalArgumentException e){
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }

    public void updateItemsCountById(Long id, Integer numberOfItems) {
        try {
            cartRepository.updateItemsCountById(UserService.loggedInUser.getLogin(), id, numberOfItems);
        }
        catch (IllegalArgumentException e){
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }

}

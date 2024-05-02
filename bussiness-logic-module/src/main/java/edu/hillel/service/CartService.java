package edu.hillel.service;

import java.util.HashMap;
import java.util.Map;

import edu.hillel.entities.Cart;
import edu.hillel.entities.Item;
import edu.hillel.repository.ItemRepository;

public class CartService {
    private static Cart cart;
    private ItemRepository itemRepository;

    public CartService() {
        cart = new Cart();
        cart.setAddedItems(new HashMap<>());
        cart.setUser(UserService.loggedInUser);
    }

    public CartService(ItemRepository itemRepository) {
        this();
        this.itemRepository = itemRepository;
    }

    public void addItemToCart(Long itemId, Integer numberOfItems) {
        cart.getAddedItems().computeIfPresent(itemId, (k, v) -> {
            v = v + numberOfItems;
            return v;
        });
        if (!cart.getAddedItems().containsKey(itemId)) {
            cart.getAddedItems().put(itemId, numberOfItems);
        }
    }

    public String getCartContent() {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Long, Integer> addedItems = cart.getAddedItems();
        for (Map.Entry<Long, Integer> entry : addedItems.entrySet()) {
            Item itemById = itemRepository.getItemById(entry.getKey());
            stringBuilder.append(itemById.getItemName()).append("\n");
        }
        return stringBuilder.toString();
    }

    public Double getTotalCartAmount() {
        Double totalAmount = 0.0;
        Map<Long, Integer> addedItems = cart.getAddedItems();
        for (Map.Entry<Long, Integer> entry : addedItems.entrySet()) {
            Item itemById = itemRepository.getItemById(entry.getKey());
            totalAmount += itemById.getPrice() * entry.getValue();
        }

        return totalAmount;
    }


}

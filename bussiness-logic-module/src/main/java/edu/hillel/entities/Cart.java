package edu.hillel.entities;

import java.util.Collections;
import java.util.Map;

import lombok.Data;

@Data
public class Cart {
    private Map<Long, Integer> addedItems;
    private String ownerOfCart;

    public void addItemToCart(Long itemId, Integer numberOfItems) {
        getAddedItems().computeIfPresent(itemId, (k, v) -> {
            v = v + numberOfItems;
            return v;
        });
        if (!getAddedItems().containsKey(itemId)) {
            getAddedItems().put(itemId, numberOfItems);
        }
    }

    public void removeAllItemsById(Long id) {
        if (!getAddedItems().containsKey(id))
            throw new IllegalArgumentException(String.format("Item with ID %d is not exist in the cart\n", id));
        getAddedItems().remove(id);
    }

    public void updateItemsCountById(Long id, Integer numberOfItems) {
        if (numberOfItems < 0)
            throw new IllegalArgumentException("Number of items can't be negative");
        else if (numberOfItems == 0)
            removeAllItemsById(id);
        else {
            if (!getAddedItems().containsKey(id))
                addItemToCart(id, numberOfItems);
            else
                getAddedItems().replace(id, numberOfItems);
        }
    }
}

package edu.hillel.repository.cart;

import java.util.Map;

public interface CartRepository {
    void addItemToCart(String userLogin, Long itemId, Integer numberOfItems);

    Map<Long, Integer> getAddedItems(String userLogin);

    void removeAllItemsById(String userLogin, Long id);

    void updateItemsCountById(String userLogin, Long id, Integer numberOfItems);
}

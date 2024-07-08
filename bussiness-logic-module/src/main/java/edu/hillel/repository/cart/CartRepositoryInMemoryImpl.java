package edu.hillel.repository.cart;

import edu.hillel.entities.Cart;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepositoryInMemoryImpl implements CartRepository {
    private Map<String, Cart> carts;
    private static CartRepositoryInMemoryImpl singletonImpl;

    public static synchronized CartRepositoryInMemoryImpl getSingletonInstance() {
        if (singletonImpl == null) {
            singletonImpl = new CartRepositoryInMemoryImpl();
        }
        return singletonImpl;
    }

    private CartRepositoryInMemoryImpl() {
        initialize();
    }

    private void initialize() {
        carts = new HashMap<>();
    }

    private Cart getOrAddCartByUserLogin(String userLogin) {
        if (!carts.containsKey(userLogin)) {
            Cart cart = new Cart();
            cart.setAddedItems(new HashMap<>());
            cart.setOwnerOfCart(userLogin);
            carts.put(userLogin, cart);
            return cart;
        }
        return carts.get(userLogin);
    }

    @Override
    public void addItemToCart(String userLogin, Long itemId, Integer numberOfItems) {
        Cart cart = getOrAddCartByUserLogin(userLogin);
        cart.addItemToCart(itemId, numberOfItems);
    }

    @Override
    public Map<Long, Integer> getAddedItems(String userLogin) {
        Cart cart = getOrAddCartByUserLogin(userLogin);
        return Collections.unmodifiableMap(cart.getAddedItems());
    }

    @Override
    public void removeAllItemsById(String userLogin, Long id) {
        Cart cart = getOrAddCartByUserLogin(userLogin);
        cart.removeItemFromCartById(id);
    }

    @Override
    public void updateItemsCountById(String userLogin, Long id, Integer numberOfItems) {
        Cart cart = getOrAddCartByUserLogin(userLogin);
        cart.updateItemsCountById(id, numberOfItems);
    }
}


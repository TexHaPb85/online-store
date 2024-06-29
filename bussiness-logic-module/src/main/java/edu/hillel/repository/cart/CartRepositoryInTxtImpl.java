package edu.hillel.repository.cart;

import edu.hillel.entities.Cart;
import edu.hillel.entities.Item;
import edu.hillel.utilities.FileUtils;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Repository
public class CartRepositoryInTxtImpl implements CartRepository {
    private Map<String, Cart> carts;
    private static CartRepositoryInTxtImpl singletonImpl;
    private final String TXT_FILE_PATH = "data/carts.txt";

    public static synchronized CartRepositoryInTxtImpl getSingletonInstance(){
        if (singletonImpl == null){
            singletonImpl = new CartRepositoryInTxtImpl();
        }
        return singletonImpl;
    }

    private CartRepositoryInTxtImpl() {
        initialize();
    }
    private void initialize() {
        carts = new HashMap<>();
        loadTxtFile();
    }

    private void loadTxtFile(){
        List<Item> items = new ArrayList<>();
        String fileContent = FileUtils.readFileContent(TXT_FILE_PATH);

        carts.clear();

        Cart loadingCart = null;
        int cartLoadStep = 0;
        int itemEntriesCountLeft = 0;

        for (String line : fileContent.split("\n")) {
            switch (cartLoadStep){
                // user login
                case 0: {
                    loadingCart = new Cart();
                    loadingCart.setAddedItems(new HashMap<>());
                    loadingCart.setOwnerOfCart(line);
                    carts.put(line, loadingCart);
                    cartLoadStep++;
                    break;
                }
                // item entries count
                case 1: {
                    itemEntriesCountLeft = Integer.valueOf(line);
                    cartLoadStep++;
                    break;
                }
                // item entries
                case 2: {
                    String[] separateWord = line.split(" ");
                    loadingCart.addItemToCart(Long.valueOf(separateWord[0]), Integer.valueOf(separateWord[1]));

                    itemEntriesCountLeft--;
                    if (itemEntriesCountLeft == 0)
                        cartLoadStep = 0;
                    break;
                }
            }
        }
    }

    private void saveTxtFile(){
        List<Item> items = new ArrayList<>();

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String txtPathStr = rootPath + TXT_FILE_PATH;

        if (!txtPathStr.isEmpty() && txtPathStr.charAt(0) == '/')
            txtPathStr = txtPathStr.substring(1, txtPathStr.length());

        Path txtPath = Paths.get(txtPathStr);

        try {
            Files.createDirectories(txtPath.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(txtPathStr)) {
            carts.forEach((k,v) -> {
                try {
                    writer.write(v.getOwnerOfCart());
                    writer.write("\n");
                    writer.write(Integer.toString(v.getAddedItems().size()));
                    writer.write("\n");
                    v.getAddedItems().forEach((ik, iv) ->{
                        try{
                            writer.write(Integer.toString(ik.intValue()));
                            writer.write(" ");
                            writer.write(Integer.toString(iv));
                            writer.write("\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            //writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Cart getOrAddCartByUserLogin(String userLogin){
        if (!carts.containsKey(userLogin)){
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
        saveTxtFile();
    }

    @Override
    public Map<Long, Integer> getAddedItems(String userLogin) {
        Cart cart = getOrAddCartByUserLogin(userLogin);
        return Collections.unmodifiableMap(cart.getAddedItems());
    }

    @Override
    public void removeAllItemsById(String userLogin, Long id) {
        Cart cart = getOrAddCartByUserLogin(userLogin);
        cart.removeAllItemsById(id);
        saveTxtFile();
    }

    @Override
    public void updateItemsCountById(String userLogin, Long id, Integer numberOfItems) {
        Cart cart = getOrAddCartByUserLogin(userLogin);
        cart.updateItemsCountById(id, numberOfItems);
        saveTxtFile();
    }
}


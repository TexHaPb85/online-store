package edu.hillel.service;

import java.util.List;

import edu.hillel.entities.Item;
import edu.hillel.entities.User;
import edu.hillel.repository.ItemRepository;
import edu.hillel.repository.ItemRepositoryTxtFilesStorageImpl;

public class ItemService {
    private ItemRepository itemRepository;

    private static ItemService singltonImpl;

    public static ItemService getSingeltonInstance(ItemRepository itemRepository){
        if(singltonImpl == null){
            singltonImpl = new ItemService(itemRepository);
        }
        return singltonImpl;
    }

    private ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private ItemService() {

    }

    public List<Item> getAllItems() {
        List<Item> allItems = itemRepository.getAllItems();
        if(allItems.isEmpty()) {
            throw new RuntimeException("No items in storage");
        }
        return  allItems;
    }

    public void addNewItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        itemRepository.addNewItem(item);
    }
}

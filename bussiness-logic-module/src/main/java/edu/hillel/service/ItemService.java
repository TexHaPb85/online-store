package edu.hillel.service;

import java.util.List;
import java.util.NoSuchElementException;

import edu.hillel.entities.Item;
import edu.hillel.repository.item.ItemRepository;

public class ItemService {
    private static ItemService singletonImpl;
    private ItemRepository itemRepository;

    private ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private ItemService() {
    }

    public static ItemService getSingletonInstance(ItemRepository itemRepository) {
        if (singletonImpl == null) {
            singletonImpl = new ItemService(itemRepository);
        }
        return singletonImpl;
    }

    public List<Item> getAllItems() {
        List<Item> allItems = itemRepository.getAllItems();
        if (allItems.isEmpty()) {
            throw new RuntimeException("No items in storage");
        }
        return allItems;
    }

    public void addNewItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        itemRepository.addNewItem(item);
    }

    public Item getItemById(Long itemId) {
        return getAllItems().stream()
            .filter(item -> item.getItemId().equals(itemId))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException());
    }
}

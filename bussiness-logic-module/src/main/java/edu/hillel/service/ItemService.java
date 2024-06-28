package edu.hillel.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import edu.hillel.entities.Item;
import edu.hillel.repository.item.ItemRepository;

@Service
public class ItemService {
    private static ItemService singletonImpl;

    private final ItemRepository itemRepository;

    public ItemService(@Qualifier("itemRepositoryInMemoryImpl") ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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

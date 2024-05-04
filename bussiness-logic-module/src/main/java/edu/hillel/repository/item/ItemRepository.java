package edu.hillel.repository.item;

import java.util.List;

import edu.hillel.entities.Item;

public interface ItemRepository {

    List<Item> getAllItems();

    Item getItemById(Long id);

    Item getItemByName(String name);

    void addNewItem(Item item);

    void removeItemById(Long id);

    void updateItem(Long id, Item newVersionOfItem);
}

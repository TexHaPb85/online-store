package edu.hillel.repository;

import java.util.List;

import edu.hillel.entities.Item;

public interface ItemRepository {

    List<Item> getAllItems();

    Item getItemById(Long id);
    Item getItemByName(String name);

    void addNewItem(Item item);

    void removeItemById(Long id);

}

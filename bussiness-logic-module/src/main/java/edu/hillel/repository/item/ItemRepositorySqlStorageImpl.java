package edu.hillel.repository.item;

import java.util.List;

import edu.hillel.entities.Item;

public class ItemRepositorySqlStorageImpl implements ItemRepository {
    @Override
    public List<Item> getAllItems() {
        return null;
    }

    @Override
    public Item getItemById(Long id) {
        return null;
    }

    @Override
    public Item getItemByName(String name) {
        return null;
    }

    @Override
    public void addNewItem(Item item) {

    }

    @Override
    public void removeItemById(Long id) {

    }

    @Override
    public void updateItem(Long id, Item newVersionOfItem) {

    }
}

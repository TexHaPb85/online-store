package edu.hillel.repository.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.hillel.entities.Item;
import edu.hillel.utilities.FileUtils;

@Repository
public class ItemRepositoryTxtFilesStorageImpl implements ItemRepository {

    private static ItemRepositoryTxtFilesStorageImpl singltonImpl;

    private ItemRepositoryTxtFilesStorageImpl() {
    }

    public static ItemRepositoryTxtFilesStorageImpl getSingeltonInstance() {
        if (singltonImpl == null) {
            singltonImpl = new ItemRepositoryTxtFilesStorageImpl();
        }
        return singltonImpl;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String fileContent = FileUtils.readFileContent("data/items.txt");

        for (String nextLine : fileContent.split("\n")) {
            String[] separateWord = nextLine.split("_");
            Item item = Item.builder()
                .itemId(Long.parseLong(separateWord[0]))
                .itemName(separateWord[1])
                .description(separateWord[2])
                .price(Double.parseDouble(separateWord[3]))
                .rate(Float.parseFloat(separateWord[4]))
                .instancesLeft(Integer.parseInt(separateWord[5]))
                .build();
            items.add(item);
        }

        return items;
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

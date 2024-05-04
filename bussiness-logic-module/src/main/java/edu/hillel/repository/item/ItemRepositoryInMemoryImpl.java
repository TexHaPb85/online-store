package edu.hillel.repository.item;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;

import edu.hillel.entities.Category;
import edu.hillel.entities.Item;

public class ItemRepositoryInMemoryImpl implements ItemRepository {
    private List<Item> allItems;
    private List<Category> allCategories;
    private static ItemRepositoryInMemoryImpl singltonImpl;

    public static ItemRepositoryInMemoryImpl getSingeltonInstance(){
        if(singltonImpl == null){
            singltonImpl = new ItemRepositoryInMemoryImpl();
        }
        return singltonImpl;
    }

    private ItemRepositoryInMemoryImpl() {
        initialize();
    }

    private void initialize() {
        Faker faker = new Faker();
        allItems = new ArrayList<>();
        allCategories = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            Book book = faker.book();
            Item build = Item.builder()
                .itemId((long) i)
                .itemName(book.author() + " " + book.title())
                .description(book.author() + " " + book.title() + " genre: " + book.genre())
                .rate((float) (Math.random() * 4 + 1))
                .price(Math.random() * 100 + 15)
                .instancesLeft((int) (Math.random()*30+10))
                .build();
            allItems.add(build);
        }
        Category booksCategory = new Category.CategoryBuilder()
            .name("books")
            .items(allItems)
            .build();
        allCategories.add(booksCategory);
    }

    @Override
    public List<Item> getAllItems() {
        return this.allItems;
    }

    @Override
    public Item getItemById(Long id) {
        return this.allItems.stream()
            .filter(item -> item.getItemId().equals(id))
            .findAny().orElseThrow(() -> new NoSuchElementException("No item with id " + id));
    }

    @Override
    public Item getItemByName(String name) {
        return this.allItems.stream()
            .filter(item -> item.getItemName().equals(name))
            .findAny().orElseThrow(() -> new NoSuchElementException("No item with name " + name));
    }

    @Override
    public void addNewItem(Item item) {
        allItems.add(item);
    }

    @Override
    public void removeItemById(Long id) {
        allItems.removeIf(item -> item.getItemId().equals(id));
    }

    @Override
    public void updateItem(Long id, Item newVersionOfItem) {

    }
}

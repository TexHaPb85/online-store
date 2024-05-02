package edu.hillel;

import edu.hillel.entities.Item;
import edu.hillel.repository.ItemRepository;
import edu.hillel.repository.ItemRepositoryInMemoryImpl;
import edu.hillel.repository.ItemRepositoryTxtFilesStorageImpl;
import edu.hillel.service.ItemService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ItemRepository itemRepository = ItemRepositoryTxtFilesStorageImpl.getSingeltonInstance();
////        ItemRepositoryTxtFilesStorageImpl itemRepositoryTxtFilesStorage = new ItemRepositoryTxtFilesStorageImpl();
////        itemRepositoryTxtFilesStorage.getAllItems();
        ItemService itemService = ItemService.getSingeltonInstance(itemRepository);
        itemService.getAllItems().forEach(item -> System.out.println(item));
    }
}

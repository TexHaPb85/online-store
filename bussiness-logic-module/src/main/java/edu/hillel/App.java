package edu.hillel;

import edu.hillel.repository.item.ItemRepository;
import edu.hillel.repository.item.ItemRepositoryTxtFilesStorageImpl;
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
        ItemService itemService = ItemService.getSingletonInstance(itemRepository);
        itemService.getAllItems().forEach(item -> System.out.println(item));
    }
}

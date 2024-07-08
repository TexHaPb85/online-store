package edu.hillel;

import edu.hillel.entities.Category;
import edu.hillel.repository.category.CategoryRepository;
import edu.hillel.repository.category.CategoryRepositorySQLImpl;
import edu.hillel.service.CategoryService;

public class TestSQLCategory {
    public static void main(String[] args) {

        CategoryRepository categoryRepository = CategoryRepositorySQLImpl.getSingletonInstance();
        CategoryService categoryService = CategoryService.getSingletonInstance(categoryRepository);

        categoryService.getAllCategory().forEach(category -> System.out.println(category.toString()));
        Category car = new Category.CategoryBuilder().name("Car").build();
        categoryService.addCategory(car);

        categoryService.getAllCategory().forEach(category -> System.out.println(category.toString()));
    }
}

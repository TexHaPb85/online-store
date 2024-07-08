package edu.hillel;

import edu.hillel.entities.Category;
import edu.hillel.repository.category.CategoryRepository;
import edu.hillel.repository.category.CategoryRepositoryTxtFilesStorageImpl;
import edu.hillel.service.CategoryService;

public class TestCategory {
    public static void main(String[] args) {

        CategoryRepository singletonInstance = CategoryRepositoryTxtFilesStorageImpl.getSingletonInstance();
        CategoryService categoryService = CategoryService.getSingletonInstance(singletonInstance);

        categoryService.getAllCategory()
                .forEach(category -> System.out.println(category));

        Category samsung = new Category.CategoryBuilder()
                .name("Samsung")
                .parentCategory(categoryService.getAllCategory()
                        .stream()
                        .filter(category -> category.getCategoryName().equals("Electronic"))
                        .findFirst()
                        .get())
                .build();
        categoryService.addCategory(samsung);

        categoryService.removeCategory("iPhone");

        /*categoryService.addCategory(new Category.CategoryBuilder()
                .name("Books")
                .parentCategory(categoryService.getAllCategory()
                        .stream()
                        .filter(category -> category.getCategoryName().equals("Main"))
                        .findFirst()
                        .get())
                .build());

        Category smartphone = new Category.CategoryBuilder()
                .name("Smartphone")
                .parentCategory(categoryService.getAllCategory()
                        .stream()
                        .filter(category -> category.getCategoryName().equals("Main"))
                        .findFirst()
                        .get())
                .build();
        categoryService.addCategory(smartphone);

        Category electronic = new Category.CategoryBuilder()
                .name("Electronic")
                .parentCategory(categoryService.getAllCategory()
                        .stream()
                        .filter(category -> category.getCategoryName().equals("Main"))
                        .findFirst()
                        .get())
                .subCategories(List.of(smartphone))
                .build();
        categoryService.addCategory(electronic);

        categoryService.getAllCategory()
                .forEach(category -> System.out.println(category));

//        categoryService.removeCategory(electronic);

        System.out.println();
        categoryService.getAllCategory()
                .forEach(category -> System.out.println(category));

        Category updatedSmartphone = new Category.CategoryBuilder()
                .name("iPhone")
                .parentCategory(categoryService.getAllCategory()
                        .stream()
                        .filter(category -> category.getCategoryName().equals("Electronic"))
                        .findFirst()
                        .get())
                .build();

        categoryService.updateCategory("Smartphone", updatedSmartphone);
        System.out.println();
        categoryService.getAllCategory()
                .forEach(category -> System.out.println(category));*/
    }
}

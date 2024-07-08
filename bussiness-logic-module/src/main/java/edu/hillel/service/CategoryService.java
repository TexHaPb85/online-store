package edu.hillel.service;

import edu.hillel.entities.Category;
import edu.hillel.repository.category.CategoryRepository;

import java.util.List;
import java.util.Objects;

public class CategoryService {

    private CategoryRepository categoryRepository;
    private static CategoryService categoryService;

    private CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static CategoryService getSingletonInstance(CategoryRepository categoryRepository) {
        if (categoryService == null) {
            categoryService = new CategoryService(categoryRepository);
        }
        return categoryService;
    }

    public List<Category> getAllCategory() {
        return categoryRepository.getAllCategory();
    }

    public void addCategory(Category category) {
        if (Objects.isNull(category)) {
            throw new IllegalArgumentException("User is null");
        }
        boolean isCategoryExist = getAllCategory().stream()
                .anyMatch(category1 -> category1.getCategoryName().equals(category.getCategoryName()));
        if (!isCategoryExist) {
            categoryRepository.addCategory(category);
        } else {
            System.out.println("Cannot add this category. Category already exist");
        }
    }

    public void removeCategory(String categoryName) {
        categoryRepository.removeCategory(categoryName);
    }

    public void updateCategory(String categoryName, Category updatedCategory) {
        if (Objects.isNull(updatedCategory)) {
            throw new IllegalArgumentException("User is null");
        }
        boolean isCategoryExist = getAllCategory().stream()
                .anyMatch(category1 -> category1.getCategoryName().equals(updatedCategory.getCategoryName()));
        if (!isCategoryExist) {
            categoryRepository.updateCategory(categoryName, updatedCategory);
        } else {
            System.out.println("Cannot add this category. Category already exist");
        }
    }

}

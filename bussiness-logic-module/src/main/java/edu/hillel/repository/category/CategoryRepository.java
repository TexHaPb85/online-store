package edu.hillel.repository.category;

import edu.hillel.entities.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> getAllCategory();

    void addCategory(Category category);

    void removeCategory(String categoryName);

    void updateCategory(String categoryName, Category updatedCategory);

}

package edu.hillel.repository.category;

import edu.hillel.entities.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CategoryRepositoryInMemoryImpl implements CategoryRepository {

    private List<Category> categories = new ArrayList<>();
    private static CategoryRepositoryInMemoryImpl singleton;

    public static CategoryRepositoryInMemoryImpl getSingletonInstance() {
        if (singleton == null) {
            singleton = new CategoryRepositoryInMemoryImpl();
        }
        return singleton;
    }

    private CategoryRepositoryInMemoryImpl() {
        initialize();
    }

    private void initialize() {
        categories.add(new Category.CategoryBuilder()
                .name("Main")
                .build());
    }

    @Override
    public List<Category> getAllCategory() {
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        if (category.getParentCategory() != null) {
            Category categoryParent = getAllCategory().stream()
                    .filter(categoryP -> categoryP.getCategoryName().equals(category.getParentCategory().getCategoryName()))
                    .findFirst()
                    .get();

            List<Category> subParentCategories = categoryParent.getSubCategories();
            if (subParentCategories == null) {
                categoryParent.setSubCategories(new ArrayList<>());
            }
            subParentCategories = categoryParent.getSubCategories();

            List<Category> subCategories = category.getSubCategories();
            if (subCategories != null) {
                subParentCategories.removeIf(subCategories::contains);
            }
            subParentCategories.add(category);
        }
        categories.add(category);
    }

    @Override
    public void removeCategory(String categoryName) {
        Category category = getAllCategory().stream()
                .filter(categ -> categ.getCategoryName().equals(categoryName))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No such element exist"));

        Category parentCategory = category.getParentCategory();
        List<Category> subCategories = category.getSubCategories();
        if (parentCategory == null && subCategories != null) {
            for (Category subCategory : subCategories) {
                subCategory.setParentCategory(null);
            }
        }
        if (parentCategory != null && subCategories != null) {
            for (Category subCategory : subCategories) {
                parentCategory.getSubCategories().remove(category);
                parentCategory.getSubCategories().add(subCategory);
                subCategory.setParentCategory(parentCategory);
            }
        }
        if (parentCategory != null && subCategories == null) {
            parentCategory.setSubCategories(null);
        }
        categories.removeIf(categoryDel -> categoryDel.equals(category));
    }

    @Override
    public void updateCategory(String categoryName, Category updatedCategory) {
        Optional<Category> categoryOld = categories.stream()
                .filter(category -> category.getCategoryName().equals(categoryName))
                .findFirst();
        categoryOld.ifPresent(category -> {
            category.setCategoryName(updatedCategory.getCategoryName());
            category.setParentCategory(updatedCategory.getParentCategory());
            category.setSubCategories(updatedCategory.getSubCategories());
            category.setItems(updatedCategory.getItems());
        });
    }
}

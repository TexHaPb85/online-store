package edu.hillel.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Category implements Cloneable {
    private String categoryName;
    private Category parentCategory;
    private List<Category> subCategories;
    private List<Item> items;

    public Category(String categoryName, Category parentCategory, List<Category> subCategories, List<Item> items) {
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.subCategories = subCategories;
        this.items = items;
    }

    public Category() {
    }

    public static Category getInstance() {
        return new Category();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Category clone = new Category();
        clone.categoryName = categoryName;
        clone.parentCategory = parentCategory;
        clone.subCategories = subCategories;
        clone.items = items;
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(categoryName);

        if (parentCategory != null) {
            stringBuilder.append("_").append(parentCategory.getCategoryName());
        } else {
            stringBuilder.append("_null");
        }
        if (subCategories != null) {
            List<String> list = subCategories.stream()
                    .map(Category::getCategoryName)
                    .toList();
            stringBuilder.append("_").append(list);
        } else {
            stringBuilder.append("_null");
        }
        return String.valueOf(stringBuilder);

    }

    public static class CategoryBuilder {
        private Category category;

        public CategoryBuilder() {
            this.category = new Category();
        }

        public CategoryBuilder name(String name) {
            category.categoryName = name;
            return this;
        }

        public CategoryBuilder parentCategory(Category parentCategory) {
            category.parentCategory = parentCategory;
            return this;
        }

        public CategoryBuilder subCategories(List<Category> subCategories) {
            category.subCategories = subCategories;
            return this;
        }

        public CategoryBuilder items(List<Item> items) {
            category.items = items;
            return this;
        }

        public Category build() {
            Category cloneCategory;
            try {
                cloneCategory = (Category) this.category.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            this.category = new Category();
            return cloneCategory;
        }
    }
}

package edu.hillel.entities;

import java.util.List;


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

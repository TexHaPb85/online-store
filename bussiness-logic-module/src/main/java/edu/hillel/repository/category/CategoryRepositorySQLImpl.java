package edu.hillel.repository.category;

import edu.hillel.entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.hillel.constants.Constants.*;

public class CategoryRepositorySQLImpl implements CategoryRepository {

    private static CategoryRepositorySQLImpl singleton;

    public static CategoryRepositorySQLImpl getSingletonInstance() {
        if (singleton == null) {
            singleton = new CategoryRepositorySQLImpl();
        }
        return singleton;
    }

    private CategoryRepositorySQLImpl() {
    }

    @Override
    public List<Category> getAllCategory() {
        Map<String, Category> singleCategories = new HashMap<>();
        List<Category> categories = new ArrayList<>();

        String query = "WITH RECURSIVE catalog_tree AS (\n" +
                "SELECT category_name, parent_category_name FROM categories\n" +
                "WHERE parent_category_name ISNULL\n" +
                "UNION ALL\n" +
                "SELECT c.category_name, c.parent_category_name FROM categories c\n" +
                "INNER JOIN catalog_tree ct ON c.parent_category_name = ct.category_name)\n" +
                "SELECT * FROM catalog_tree;";

        try (Connection connection = DriverManager.getConnection(URL_TO_DB, USER_DB, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String categoryName = resultSet.getString("category_name");
                String parentCategoryName = resultSet.getString("parent_category_name");

                Category category = singleCategories.get(categoryName);
                if (category == null) {
                    category = new Category();
                    category.setCategoryName(categoryName);
                    singleCategories.put(categoryName, category);
                }
                if (parentCategoryName != null) {
                    Category parentCategory = singleCategories.get(parentCategoryName);
                    if (parentCategory == null) {
                        parentCategory = new Category();
                        parentCategory.setCategoryName(parentCategoryName);
                        singleCategories.put(parentCategoryName, parentCategory);
                    }
                    category.setParentCategory(parentCategory);
                    parentCategory.addSubCategory(category);
                }
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        String query = "INSERT INTO categories (category_name, parent_category_name) VALUES (?,?)";

        try (Connection connection = DriverManager.getConnection(URL_TO_DB, USER_DB, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.getCategoryName());
            if (category.getParentCategory() != null) {
                preparedStatement.setString(2, category.getParentCategory().getCategoryName());
            } else {
                preparedStatement.setString(2, null);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCategory(String categoryName) {
        String query = "DELETE FROM categories WHERE categories.category_name = ?";
        try (Connection connection = DriverManager.getConnection(URL_TO_DB, USER_DB, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, categoryName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(String categoryName, Category updatedCategory) {
        String query = "UPDATE categories SET category_name = ? , parent_category_name = ? \n" +
                "WHERE category_name = ?";

        try (Connection connection = DriverManager.getConnection(URL_TO_DB, USER_DB, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, updatedCategory.getCategoryName());
            if (updatedCategory.getParentCategory() != null) {
                preparedStatement.setString(2, updatedCategory.getParentCategory().getCategoryName());
            } else {
                preparedStatement.setString(2, null);
            }
            preparedStatement.setString(3, categoryName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

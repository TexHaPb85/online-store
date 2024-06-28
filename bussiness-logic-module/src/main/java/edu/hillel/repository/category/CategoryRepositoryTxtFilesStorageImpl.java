package edu.hillel.repository.category;

import edu.hillel.entities.Category;
import edu.hillel.utilities.FileUtils;

import java.util.*;

import static edu.hillel.constants.Constants.PATH_TO_CATEGORIES_FILE;

public class CategoryRepositoryTxtFilesStorageImpl implements CategoryRepository {

    private static CategoryRepositoryTxtFilesStorageImpl singletonImpl;
    private List<Category> categories = new ArrayList<>();
    private Set<Category> uniqueCategory = new HashSet<>();

    private CategoryRepositoryTxtFilesStorageImpl() {
        initialize();
    }

    private void initialize() {

        String fileContent = FileUtils.readFileContent(PATH_TO_CATEGORIES_FILE);
        String[] contentByLine = fileContent.split("\n");
        for (String line : contentByLine) {
            String[] splitLine = line.split("_");
            uniqueCategory.add(new Category.CategoryBuilder().name(splitLine[0]).build());
        }
        Category parentCategory = null;
        for (String line : contentByLine) {
            String[] splitLine = line.split("_");
            if (!splitLine[1].equals("null")) {
                parentCategory = uniqueCategory.stream()
                        .filter(category -> category.getCategoryName().equals(splitLine[1]))
                        .findAny()
                        .orElseThrow(() -> new NoSuchElementException("No such parent category"));
            }
            List<Category> subCategory = null;
            if (!splitLine[2].equals("null")) {
                subCategory = new ArrayList<>();
                for (String subSeparatedCategory : splitLine[2].split(",")) {
                    subCategory.add(uniqueCategory.stream()
                            .filter(category -> category.getCategoryName().equals(subSeparatedCategory.trim()))
                            .findAny()
                            .orElseThrow(() -> new NoSuchElementException("No such subCategory category")));
                }
            }
            categories.add(new Category.CategoryBuilder()
                    .name(splitLine[0])
                    .parentCategory(parentCategory)
                    .subCategories(subCategory)
                    .build());
        }
    }

    public static CategoryRepositoryTxtFilesStorageImpl getSingletonInstance() {
        if (singletonImpl == null) {
            singletonImpl = new CategoryRepositoryTxtFilesStorageImpl();
        }
        return singletonImpl;
    }


    @Override
    public List<Category> getAllCategory() {
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        categories.add(category);
        FileUtils.writeToFileContent(PATH_TO_CATEGORIES_FILE, "\n" + category, true);
    }

    @Override
    public void removeCategory(String categoryName) {

        String fileContent = FileUtils.readFileContent(PATH_TO_CATEGORIES_FILE);
        StringBuilder filteredContent = new StringBuilder();
        String[] newLine = fileContent.split("\n");

        for (String line : newLine) {
            String[] split = line.split("_");
            if (split[1].equals(categoryName)) {
                split[1] = categoryName;
            }
            if (split[2].contains(categoryName)) {
                split[2] = split[2].replaceAll((categoryName), "null");
            }
            if (split[0].equals(categoryName)) continue;
            filteredContent.append(split[0]).append("_")
                    .append(split[1]).append("_")
                    .append(split[2]).append("\n");
        }
        FileUtils.writeToFileContent(PATH_TO_CATEGORIES_FILE, String.valueOf(filteredContent), false);
    }

    @Override
    public void updateCategory(String categoryName, Category updatedCategory) {

        String fileContent = FileUtils.readFileContent(PATH_TO_CATEGORIES_FILE);
        StringBuilder filteredContent = new StringBuilder();
        String[] newLine = fileContent.split("\n");

        for (String line : newLine) {
            String[] split = line.split("_");

            if (split[0].equals(categoryName)) {
                split[0] = updatedCategory.getCategoryName();
                split[1] = updatedCategory.getParentCategory().getCategoryName();
                if (!split[2].equals("null")) {
                    split[2] = updatedCategory.getSubCategories().toString();
                }
                filteredContent.append(split[0]).append("_")
                        .append(split[1]).append("_")
                        .append(split[2]).append("\n");
            }
        }
        FileUtils.writeToFileContent(PATH_TO_CATEGORIES_FILE, String.valueOf(filteredContent), false);
    }
}
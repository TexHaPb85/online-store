package edu.hillel.utilities;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {
    public static String readFileContent(String pathToFile) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + pathToFile;
        StringBuilder stringBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(appConfigPath)))) {
            while (scanner.hasNext()){
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error during reading file with scanner: " + e.getMessage());
        }
        return stringBuilder.toString();
    }
}

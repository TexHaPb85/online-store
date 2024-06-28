package edu.hillel.utilities;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class FileUtils {

    private static String getPathToFile(String pathToFile) {
        String rootPath;
        try {
            URL rootUrlPath = Thread.currentThread().getContextClassLoader().getResource(pathToFile);
            rootPath = Paths.get(Objects.requireNonNull(rootUrlPath).toURI()).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return rootPath;
    }

    public static String readFileContent(String pathToFile) {
        String appConfigPath = getPathToFile(pathToFile);
        StringBuilder stringBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(appConfigPath)))) {
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error during reading file with scanner: " + e.getMessage());
        }
        return stringBuilder.toString();
    }

    public static void writeToFileContent(String pathToFile, String line, boolean append) {
        String appConfigPath = getPathToFile(pathToFile);
        try (BufferedOutputStream bufferedWriter = new BufferedOutputStream(new FileOutputStream(
                appConfigPath, append))) {
            bufferedWriter.write(line.getBytes());
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error during reading file with scanner: " + e.getMessage());
        }
    }
}
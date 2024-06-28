package edu.hillel.repository.user;

import edu.hillel.entities.User;
import edu.hillel.utilities.FileUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static edu.hillel.constants.Constants.PATH_TO_USERS_FILE;

public class UserRepositoryTxtFilesStorageImpl implements UserRepository {

    private static UserRepositoryTxtFilesStorageImpl singletonImpl;

    private UserRepositoryTxtFilesStorageImpl() {
    }

    public static UserRepositoryTxtFilesStorageImpl getSingletonInstance() {
        if (singletonImpl == null) {
            singletonImpl = new UserRepositoryTxtFilesStorageImpl();
        }
        return singletonImpl;
    }


    @Override
    public Optional<User> findUserByLoginPassword(String login, String password) {
        return getAllUsers().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findAny();
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();
        String fileContent = FileUtils.readFileContent(PATH_TO_USERS_FILE);

        String[] newLine = fileContent.split("\n");
        for (String line : newLine) {
            String[] splitLine = line.split("_");
            User user = User.builder()
                    .login(splitLine[0])
                    .password(splitLine[1])
                    .name(splitLine[2])
                    .role(User.Role.valueOf(splitLine[3]))
                    .build();
            users.add(user);
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        FileUtils.writeToFileContent(PATH_TO_USERS_FILE, "\n" + user, true);
    }


    @Override
    public void removeUser(String login) {
        String fileContent = FileUtils.readFileContent(PATH_TO_USERS_FILE);
        StringBuilder filteredContent = new StringBuilder();
        String[] newLine = fileContent.split("\n");

        for (String line : newLine) {
            String[] split = line.split("_");
            if (split[0].equals(login)) continue;
            filteredContent.append(line).append("\n");
        }
        FileUtils.writeToFileContent(PATH_TO_USERS_FILE, String.valueOf(filteredContent), false);
    }

    @Override
    public void updateUser(String login, User updatedUser) {
        String fileContent = FileUtils.readFileContent(PATH_TO_USERS_FILE);
        StringBuilder filteredContent = new StringBuilder();
        String[] newLine = fileContent.split("\n");

        for (String line : newLine) {
            String[] split = line.split("_");
            if (split[0].equals(login)) {
                line = updatedUser.toString();
            }
            filteredContent.append(line).append("\n");
        }
        FileUtils.writeToFileContent(PATH_TO_USERS_FILE, String.valueOf(filteredContent), false);

    }
}

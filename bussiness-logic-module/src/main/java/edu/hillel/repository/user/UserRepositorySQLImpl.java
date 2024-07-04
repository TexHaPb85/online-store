package edu.hillel.repository.user;

import edu.hillel.entities.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static edu.hillel.constants.Constants.*;

public class UserRepositorySQLImpl implements UserRepository {

    private static UserRepositorySQLImpl singletonUser;

    public static UserRepositorySQLImpl getSingletonInstance() {
        if (singletonUser == null) {
            singletonUser = new UserRepositorySQLImpl();
        }
        return singletonUser;
    }

    @Override
    public Optional<User> findUserByLoginPassword(String login, String password) {
        User user = null;
        String query = "SELECT * FROM users u \n" +
                "LEFT JOIN roles r ON u.role_id = r.role_id \n" +
                "WHERE login = ? AND \"password\" = ?";

        try (Connection connection = DriverManager.getConnection(URL_TO_DB, USER_DB, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = mapResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();
        String query = "SELECT * FROM users u \n" +
                "LEFT JOIN roles r ON u.role_id = r.role_id";

        try (Connection connection = DriverManager.getConnection(URL_TO_DB, USER_DB, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User user = mapResultSetToUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO users (login, password, name, role_id) VALUES (?, ?, ?, ?)";
        String queryAdminId = "SELECT * FROM roles WHERE role_name = ?";
        int roleId = 0;

        try (Connection connection = DriverManager.getConnection(URL_TO_DB, USER_DB, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             PreparedStatement preparedStatementRole = connection.prepareStatement(queryAdminId)) {
            preparedStatementRole.setString(1, user.getRole().name());
            ResultSet resultSetRole = preparedStatementRole.executeQuery();
            if (resultSetRole.next()) {
                roleId = resultSetRole.getInt("role_id");
            }
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, roleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(String login) {
        String query = "DELETE FROM users WHERE login = ?";

        try (Connection connection = DriverManager.getConnection(URL_TO_DB, USER_DB, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(String login, User updatedUser) {
        String query = "UPDATE users SET login = ?, \"password\" = ?, \"name\" = ?, role_id = ? " +
                "WHERE login = ?";
        String queryAdminId = "SELECT * FROM roles WHERE role_name = ?";
        int roleId = 2;
        try (Connection connection = DriverManager.getConnection(URL_TO_DB, USER_DB, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             PreparedStatement preparedStatementRole = connection.prepareStatement(queryAdminId)) {
            preparedStatementRole.setString(1, updatedUser.getRole().name());
            ResultSet resultSetRole = preparedStatementRole.executeQuery();
            if (resultSetRole.next()) {
                roleId = resultSetRole.getInt("role_id");
            }
            preparedStatement.setString(1, updatedUser.getLogin());
            preparedStatement.setString(2, updatedUser.getPassword());
            preparedStatement.setString(3, updatedUser.getName());
            preparedStatement.setInt(4, roleId);
            preparedStatement.setString(5, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .name(resultSet.getString("name"))
                .role(User.Role.valueOf(resultSet.getString("role_name")))
                .build();
    }

}

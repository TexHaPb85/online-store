package edu.hillel.repository.item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.hillel.entities.Item;


@Repository
public class ItemRepositorySqlStorageImpl implements ItemRepository {

    private String url = "jdbc:postgresql://localhost:5432/online-store";
    private String user = "postgres";
    private String password = "Neskazu2590";

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Item item = mapResultSetToItem(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item getItemById(Long id) {
        Item item = null;
        String query = "SELECT * FROM items WHERE item_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                item = mapResultSetToItem(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Item getItemByName(String name) {
        Item item = null;
        String query = "SELECT * FROM items WHERE item_name = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                item = mapResultSetToItem(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void addNewItem(Item item) {
        String query = "INSERT INTO items (item_name, description, price, rate, instances_left) " +
            "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, item.getItemName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setFloat(4, item.getRate());
            preparedStatement.setInt(5, item.getInstancesLeft());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeItemById(Long id) {
        String query = "DELETE FROM items WHERE item_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItem(Long id, Item newVersionOfItem) {
        String query = "UPDATE items SET item_name = ?, description = ?, price = ?, rate = ?, instances_left = ? " +
            "WHERE item_id = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newVersionOfItem.getItemName());
            preparedStatement.setString(2, newVersionOfItem.getDescription());
            preparedStatement.setDouble(3, newVersionOfItem.getPrice());
            preparedStatement.setFloat(4, newVersionOfItem.getRate());
            preparedStatement.setInt(5, newVersionOfItem.getInstancesLeft());
            preparedStatement.setLong(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to map ResultSet to Item object
    private Item mapResultSetToItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setItemId(resultSet.getLong("item_id"));
        item.setItemName(resultSet.getString("item_name"));
        item.setDescription(resultSet.getString("description"));
        item.setPrice(resultSet.getDouble("price"));
        item.setRate(resultSet.getFloat("rate"));
        item.setInstancesLeft(resultSet.getInt("instances_left"));
        return item;
    }
}

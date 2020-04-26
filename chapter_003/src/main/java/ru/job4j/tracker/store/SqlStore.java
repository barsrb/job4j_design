package ru.job4j.tracker.store;

import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlStore implements Store {
    private Connection cn;

    public SqlStore() {
        this.init();
    }

    @Override
    public void init() {
        try (InputStream in = SqlStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Item add(Item item) {
        try {
            PreparedStatement ps = cn.prepareStatement("INSERT INTO tracker (name) VALUES (?);", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getName());
            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                item.setId(generatedKey.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        try {
            PreparedStatement ps = cn.prepareStatement("UPDATE tracker SET name = ? WHERE id::text = ?");
            ps.setString(1, item.getName());
            ps.setString(2, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            PreparedStatement ps = cn.prepareStatement("DELETE FROM tracker WHERE id::text = ?;");
            ps.setString(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try {
            Statement st = cn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM tracker");
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"));
                item.setId(resultSet.getString("id"));
                result.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try {
            PreparedStatement st = cn.prepareStatement("SELECT * FROM tracker WHERE name like ?");
            st.setString(1, "%" + key + "%");
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"));
                item.setId(resultSet.getString("id"));
                result.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        try {
            PreparedStatement st = cn.prepareStatement("SELECT * FROM tracker WHERE id::text = ?");
            st.setString(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"));
                item.setId(resultSet.getString("id"));
                return item;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}

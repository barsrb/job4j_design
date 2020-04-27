package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.store.SqlStore;
import ru.job4j.tracker.store.Store;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class TrackerTest {
    private Connection init() {
        Connection cn;
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
        return cn;
    }


    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void addThreeItemsAndGetSecond() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item result = tracker.findById(item2.getId());
        assertThat(result.getName(), is(item2.getName()));
    }

    @Test
    public void addFourItemsAndGetTwoByName() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item item1 = new Item("test1");
        Item item2 = new Item("testname");
        Item item3 = new Item("test3");
        Item item4 = new Item("testname");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        List<Item> results = tracker.findByName("testname");
        List<Item> expect = new ArrayList<>();
        expect.add(item2);
        expect.add(item4);
        assertThat(expect, is(results));
    }

    @Test
    public void addThreeItemsButNotFoundByID() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item result = tracker.findById("none");
        assertNull(result);
    }

    @Test
    public void addFourItemsAndGetAll() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item item1 = new Item("test1");
        Item item2 = new Item("testname");
        Item item3 = new Item("test3");
        Item item4 = new Item("testname");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        List<Item> results = tracker.findAll();
        List<Item> expect = new ArrayList<>(Arrays.asList(item1, item2, item3, item4));
        assertThat(results, is(expect));
    }

    @Test
    public void whenReplace() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenNotReplace() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace("none", bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug"));
    }

    @Test
    public void whenDelete() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        assertTrue(tracker.delete(id));
    }

    @Test
    public void whenNotDelete() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item bug = new Item("Bug");
        tracker.add(bug);
        assertFalse(tracker.delete("none"));
    }

    @Test
    public void addFourItemsThenDeleteAndGetAll() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("test4");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.delete(item2.getId());
        List<Item> results = tracker.findAll();
        List<Item> expect = new ArrayList<>(Arrays.asList(item1, item3, item4));
        assertThat(results, is(expect));
    }

    @Test
    public void addFourItemsThenDeleteAndAddNewAndGetAll() {
        Store tracker = new SqlStore(ConnectionRollback.create(this.init()));
        Item item1 = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test3");
        Item item4 = new Item("test4");
        Item item5 = new Item("test5");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.add(item4);
        tracker.delete(item2.getId());
        tracker.add(item5);
        List<Item> results = tracker.findAll();
        List<Item> expect = new ArrayList<>(Arrays.asList(item1, item3, item4, item5));
        assertThat(results, is(expect));
    }
}
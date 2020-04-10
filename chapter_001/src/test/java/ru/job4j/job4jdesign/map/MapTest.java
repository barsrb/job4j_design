package ru.job4j.job4jdesign.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    @Test
    public void printMap() {
        Map<User, Object> map = new HashMap<>();
        User user1 = new User("Ivan", 1, null);
        User user2 = new User("Ivan", 1, null);
        map.put(user1, "user1");
        map.put(user2, "user2");
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1.equals(user2));
        System.out.println(map);
    }

}
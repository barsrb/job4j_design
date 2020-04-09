package ru.job4j.job4jdesign.list;

import org.junit.Test;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleSetTest {

    @Test
    public void addThreeUniqueElements() {
        SimpleSet<String> ss = new SimpleSet<>();
        ss.add("test1");
        ss.add("test2");
        ss.add("test3");
        assertThat(ss, containsInAnyOrder("test1", "test2", "test3"));
    }

    @Test
    public void addThreeNotUniqueElements() {
        SimpleSet<String> ss = new SimpleSet<>();
        ss.add("test3");
        ss.add("test1");
        ss.add("test1");
        ss.add("test2");
        ss.add("test2");
        assertThat(ss, containsInAnyOrder("test1", "test2", "test3"));
    }


}
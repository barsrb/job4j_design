package ru.job4j.job4jdesign.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class SimpleMapTest {

    @Test
    public void addThreeObjectsAndSizeIsThree() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(new Object(), new Object());
        map.put(new Object(), new Object());
        map.put(new Object(), new Object());
        assertThat(map.getSize(), is(3));
    }

    @Test
    public void addThreeObjectsWithSameHashAndSizeIsThree() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(1 + 16, "test2");
        map.put(1 + 32, "test3");
        assertThat(map.getSize(), is(3));
    }

    @Test
    public void addThreeObjectsWithSameHashAndGetFirst() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(1 + 16, "test2");
        map.put(1 + 32, "test3");
        assertThat(map.get(1), is("test1"));
    }

    @Test
    public void addThreeObjectsWithSameHashAndGetSecond() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(1 + 16, "test2");
        map.put(1 + 32, "test3");
        assertThat(map.get(17), is("test2"));
    }

    @Test
    public void addThreeObjectsWithSameHashAndGetLast() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(1 + 16, "test2");
        map.put(1 + 32, "test3");
        assertThat(map.get(33), is("test3"));
    }

    @Test
    public void addThreeObjectsAndReplaceOneThanGetNewValue() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(2, "test2");
        map.put(3, "test3");
        map.put(2, "newtest2");
        assertThat(map.get(2), is("newtest2"));
    }

    @Test
    public void addThreeObjectsAndReplaceOneWithReturnReplaced() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(2, "test2");
        map.put(3, "test3");
        assertThat(map.put(2, "newtest2"), is("test2"));
    }

    @Test
    public void addThreeObjectsAndReplaceOneThenSizeIsThree() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(2, "test2");
        map.put(3, "test3");
        map.put(2, "newtest3");
        assertThat(map.getSize(), is(3));
    }

    @Test
    public void addThreeObjectsAndGetNonExistsKeyReturnsNull() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(2, "test2");
        map.put(3, "test3");
        assertNull(map.get(-1));
    }

    @Test
    public void addAndGetIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(2, "test2");
        map.put(3, "test3");
        assertThat(map, containsInAnyOrder(new SimpleMap.Entry<>(1, "test1"),
                new SimpleMap.Entry<>(2, "test2"),
                new SimpleMap.Entry<>(3, "test3")));
    }

    @Test
    public void iteratorAllowManyHasNext() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(17, "test2");
        map.put(33, "test3");
        map.put(5, "test4");
        map.put(21, "test5");
        Iterator<SimpleMap.Entry<Object, Object>> iterator = map.iterator();
        iterator.hasNext();
        iterator.hasNext();
        iterator.hasNext();
        iterator.hasNext();
        iterator.hasNext();
        iterator.hasNext();
        iterator.hasNext();
        Set<SimpleMap.Entry<Object, Object>> set = Set.of(
                new SimpleMap.Entry<>(1, "test1"),
                new SimpleMap.Entry<>(17, "test2"),
                new SimpleMap.Entry<>(33, "test3"),
                new SimpleMap.Entry<>(5, "test4"),
                new SimpleMap.Entry<>(21, "test5"));
        assertThat(set, hasItem(iterator.next()));
        assertThat(set, hasItem(iterator.next()));
        assertThat(set, hasItem(iterator.next()));
        assertThat(set, hasItem(iterator.next()));
        assertThat(set, hasItem(iterator.next()));
    }


    @Test
    public void addThreeWithSameHashAndTwoWithOtherAndGetIterator() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(17, "test2");
        map.put(33, "test3");
        map.put(5, "test4");
        map.put(21, "test5");
        Iterator<SimpleMap.Entry<Object, Object>> iterator = map.iterator();
        Set<SimpleMap.Entry<Object, Object>> set = new java.util.HashSet<>(Set.of(
                new SimpleMap.Entry<>(1, "test1"),
                new SimpleMap.Entry<>(17, "test2"),
                new SimpleMap.Entry<>(33, "test3"),
                new SimpleMap.Entry<>(5, "test4"),
                new SimpleMap.Entry<>(21, "test5")));
        SimpleMap.Entry<Object, Object> entry = iterator.next();
        assertThat(set, hasItem(entry));
        set.remove(entry);
        entry = iterator.next();
        assertThat(set, hasItem(entry));
        set.remove(entry);
        entry = iterator.next();
        assertThat(set, hasItem(entry));
        set.remove(entry);
        entry = iterator.next();
        assertThat(set, hasItem(entry));
        set.remove(entry);
        entry = iterator.next();
        assertThat(set, hasItem(entry));
        set.remove(entry);
        assertThat(set.size(), is(0));
    }

    @Test (expected = NoSuchElementException.class)
    public void addThreeWithSameHashAndTwoWithOtherAndGetNoSuchElementException() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        map.put(1, "test1");
        map.put(1 + 16, "test2");
        map.put(1 + 32, "test3");
        map.put(5, "test4");
        map.put(5 + 16, "test5");
        Iterator<SimpleMap.Entry<Object, Object>> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

}
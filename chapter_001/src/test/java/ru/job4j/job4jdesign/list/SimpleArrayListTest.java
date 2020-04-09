package ru.job4j.job4jdesign.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleArrayListTest {
    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(0), is(3));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(1));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void deleteOneElement() {
        Integer value = list.delete();
        assertThat(value, is(3));
        assertThat(list.getSize(), is(2));
    }

    @Test
    public void deleteTwoElements() {
        list.delete();
        list.delete();
        assertThat(list.get(0), is(1));
    }

    @Test (expected = NoSuchElementException.class)
    public void deleteFromEmptyList() {
        list = new SimpleArrayList<>();
        list.delete();
    }

}
package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MaxMinTest {

    Comparator<Integer> comparator = Integer::compareTo;

    List<Integer> list = List.of(2, 3, 1);

    @Test
    public void textMax() {
        assertThat(MaxMin.max(list, comparator), is(3));
    }

    @Test
    public void textMin() {
        assertThat(MaxMin.min(list, comparator), is(1));
    }
}
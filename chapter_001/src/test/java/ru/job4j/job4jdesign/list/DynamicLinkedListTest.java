package ru.job4j.job4jdesign.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DynamicLinkedListTest {
    DynamicLinkedList<Integer> dynamicList;

    @Before
    public void setUp() {
        dynamicList = new DynamicLinkedList<>();
        dynamicList.add(1);
        dynamicList.add(2);
        dynamicList.add(3);
        dynamicList.add(4);
    }

    @Test
    public void defaultArraySizeIsFour() {
        assertThat(dynamicList.getSize(), is(4));
    }

    @Test
    public void arraySizeAfterTwoElementsAddedIsSix() {
        dynamicList.add(5);
        dynamicList.add(6);
        assertThat(dynamicList.getSize(), is(6));
    }

    @Test
    public void getElementFromOriginalList() {
        assertThat(dynamicList.get(2), is(3));
    }

    @Test
    public void getElementFromIncreasedList() {
        dynamicList.add(5);
        dynamicList.add(6);
        dynamicList.add(7);
        dynamicList.add(8);
        assertThat(dynamicList.get(6), is(7));
    }


    @Test
    public void getAllElementsByIterator() {
        Iterator<Integer> it = dynamicList.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    @Test
    public void iteratorReturnsTrueIfHasNext() {
        Iterator<Integer> it = dynamicList.iterator();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void iteratorReturnsFalseIfHasNotNext() {
        Iterator<Integer> it = dynamicList.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorThrowsExceptionOnWrongNext() {
        Iterator<Integer> it = dynamicList.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void getConcurrentModificationExceptionOnNextAfterAddedElement() {
        Iterator<Integer> it = dynamicList.iterator();
        it.next();
        it.next();
        dynamicList.add(5);
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void getConcurrentModificationExceptionOnHasNextAfterAddedElement() {
        Iterator<Integer> it = dynamicList.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        dynamicList.add(5);
        it.hasNext();
    }

}
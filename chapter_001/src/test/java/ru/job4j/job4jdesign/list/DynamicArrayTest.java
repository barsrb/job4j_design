package ru.job4j.job4jdesign.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DynamicArrayTest {
    DynamicArray<Integer> dynamicArray;

    @Before
    public void setUp() {
        dynamicArray = new DynamicArray<>();
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);
        dynamicArray.add(4);
    }

    @Test
    public void defaultArraySizeIsFour() {
        assertThat(dynamicArray.getSize(), is(4));
    }

    @Test
    public void arraySizeAfterTwoElementsAddedIsSix() {
        dynamicArray.add(5);
        dynamicArray.add(6);
        assertThat(dynamicArray.getSize(), is(6));
    }

    @Test
    public void getElementFromOriginalArray() {
        assertThat(dynamicArray.get(2), is(3));
    }

    @Test
    public void getElementFromIncreasedArray() {
        dynamicArray.add(5);
        dynamicArray.add(6);
        dynamicArray.add(7);
        dynamicArray.add(8);
        assertThat(dynamicArray.get(6), is(7));
    }

    @Test
    public void elementsCountAfterTwiceIncreasedAndAddedTenElementsIsFourteen() {
        dynamicArray.add(5);
        dynamicArray.add(6);
        dynamicArray.add(7);
        dynamicArray.add(8);
        dynamicArray.add(9);
        dynamicArray.add(10);
        dynamicArray.add(11);
        dynamicArray.add(12);
        dynamicArray.add(13);
        dynamicArray.add(14);
        assertThat(dynamicArray.getSize(), is(14));
    }

    @Test
    public void getAllElementsNyIterator() {
        Iterator<Integer> it = dynamicArray.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    @Test
    public void iteratorReturnsTrueIfHasNext() {
        Iterator<Integer> it = dynamicArray.iterator();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void iteratorReturnsFalseIfHasNotNext() {
        Iterator<Integer> it = dynamicArray.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorThrowsExceptionOnWrongNext() {
        Iterator<Integer> it = dynamicArray.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void getConcurrentModificationExceptionOnNextAfterAddedElement() {
        Iterator<Integer> it = dynamicArray.iterator();
        it.next();
        it.next();
        dynamicArray.add(5);
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void getConcurrentModificationExceptionOnHasNextAfterAddedElement() {
        Iterator<Integer> it = dynamicArray.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        dynamicArray.add(5);
        it.hasNext();
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getFromIndexLessZegoGetException() {
        dynamicArray.get(-1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getFromIndexMoreThenItemsCountGetException() {
        dynamicArray.get(10);
    }

}
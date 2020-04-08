package ru.job4j.job4jdesign.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleArrayTest {

    SimpleArray<Integer> simpleArray;
    @Before
    public void createArray() {
        simpleArray = new SimpleArray<>(5);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
    }

    @Test
    public void addElement() {
        simpleArray.add(5);
        assertThat(simpleArray.length(), is(5));
        assertThat(simpleArray.get(4), is(5));
    }

    @Test
    public void getFourElementsInRandomOrder() {
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(2), is(3));
        assertThat(simpleArray.get(1), is(2));
        assertThat(simpleArray.get(3), is(4));
    }

    @Test
    public void removeElement() {
        simpleArray.remove(2);
        assertThat(simpleArray.length(), is(3));
        assertThat(simpleArray.get(2), is(4));
    }

    @Test
    public void setElement() {
        simpleArray.set(2, 100);
        assertThat(simpleArray.length(), is(4));
        assertThat(simpleArray.get(2), is(100));
    }

    @Test (expected = ArrayStoreException.class)
    public void addMoreArrayLength() {
        simpleArray.add(1);
        simpleArray.add(2);
    }

    @Test (expected = NoSuchElementException.class)
    public void setInWrongPosition() {
        simpleArray.remove(3);
        simpleArray.remove(2);
        simpleArray.remove(1);
        simpleArray.set(3, 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void setInPositionMoreArrayLength() {
        simpleArray.set(10, 1);
    }

    @Test (expected = NoSuchElementException.class)
    public void removeFromWrongPosition() {
        simpleArray.remove(5);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void getFromPositionMoreArrayLength() {
        simpleArray.get(10);
    }

    @Test (expected = NoSuchElementException.class)
    public void getFromWrongPosition() {
        simpleArray.remove(3);
        simpleArray.remove(2);
        simpleArray.remove(1);
        simpleArray.get(3);
    }

    @Test
    public void getAllElementsNyIterator() {
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    @Test
    public void iteratorReturnsTrueIfHasNext() {
        Iterator<Integer> it = simpleArray.iterator();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void iteratorReturnsFalseIfHasNotNext() {
        Iterator<Integer> it = simpleArray.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorThrowsExceptionOnWrongNext() {
        Iterator<Integer> it = simpleArray.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
    }
}
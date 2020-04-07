package ru.job4j.job4jdesign;

import java.util.Iterator;

public class JaggedArrayIterator implements Iterator {

    private int[][] array;
    private int row = 0;
    private int col = 0;

    public JaggedArrayIterator(int[][] ints) {
        array = ints;
    }

    public boolean hasNext() {
        return array.length > row;
    }

    public Object next() {
        Object result =  array[row][col++];
        if (array[row].length == col) {
            row++;
            col = 0;
        }
        return result;
    }
}

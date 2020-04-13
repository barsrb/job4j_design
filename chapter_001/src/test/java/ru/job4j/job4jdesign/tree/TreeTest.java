package ru.job4j.job4jdesign.tree;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenTreeIsBinary() {
        Tree<Integer> tree = new Tree<>(4);
        tree.add(4, 2);
        tree.add(2, 1);
        tree.add(2, 3);
        tree.add(4, 6);
        tree.add(6, 7);
        tree.add(6, 5);
        assertThat(
                tree.isBinary(),
                is(true)
        );
    }

    @Test
    public void whenTreeIsBinaryWithOneElementInBranch() {
        Tree<Integer> tree = new Tree<>(4);
        tree.add(4, 2);
        tree.add(2, 1);
        tree.add(4, 6);
        tree.add(6, 7);
        assertThat(
                tree.isBinary(),
                is(true)
        );
    }

    @Test
    public void whenTreeIsNotBinary() {
        Tree<Integer> tree = new Tree<>(4);
        tree.add(4, 2);
        tree.add(2, 1);
        tree.add(2, 3);
        tree.add(4, 6);
        tree.add(6, 7);
        tree.add(6, 5);
        tree.add(6, 8);
        assertThat(
                tree.isBinary(),
                is(false)
        );
    }
}
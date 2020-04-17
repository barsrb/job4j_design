package ru.job4j.job4jdesign;

import ru.job4j.job4jdesign.Analyze.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnalyzeTest {

    @Test
    public void whenAddedThreeDeletedTheeAndUpdateThree() {
        List<User> previous = new LinkedList<>(Arrays.asList(
                new User(1, "test1"),
                new User(2, "test2"),
                new User(3, "test3"),
                new User(4, "test4"),
                new User(5, "test5"),
                new User(6, "test6"),
                new User(7, "test7"),
                new User(8, "test8"),
                new User(9, "test9")
        ));

        List<User> current = new LinkedList<>(Arrays.asList(
                new User(1, "test1"),
                new User(3, "edit3"),
                new User(5, "test5"),
                new User(7, "edit7"),
                new User(8, "test8"),
                new User(9, "edit9"),
                new User(77, "add77"),
                new User(88, "add88"),
                new User(99, "add99")
        ));

        Info info = Analyze.diff(previous, current);

        assertThat(info.added, is(3));
        assertThat(info.changed, is(3));
        assertThat(info.deleted, is(3));
    }


    @Test
    public void whenAllThreeWasDeleted() {
        List<User> previous = new LinkedList<>(Arrays.asList(
                new User(1, "test1"),
                new User(2, "test2"),
                new User(3, "test3")
         ));

        List<User> current = new LinkedList<>(Collections.emptyList());

        Info info = Analyze.diff(previous, current);
        Info expected = new Info();
        expected.added = 0;
        expected.deleted = 3;
        expected.changed = 0;
        assertThat(info, is(expected));
    }

    @Test
    public void whenAllThreeWasChanged() {
        List<User> previous = new LinkedList<>(Arrays.asList(
                new User(1, "test1"),
                new User(2, "test2"),
                new User(3, "test3")
        ));

        List<User> current = new LinkedList<>(Arrays.asList(
                new User(1, "edit1"),
                new User(2, "edit2"),
                new User(3, "edit3")
        ));

        Info info = Analyze.diff(previous, current);
        Info expected = new Info();
        expected.added = 0;
        expected.deleted = 0;
        expected.changed = 3;
        assertThat(info, is(expected));
    }

    @Test
    public void whenAllThreeWasAdded() {
        List<User> previous = new LinkedList<>(Collections.emptyList());

        List<User> current = new LinkedList<>(Arrays.asList(
                new User(1, "test1"),
                new User(2, "test2"),
                new User(3, "test3")
        ));

        Info info = Analyze.diff(previous, current);
        Info expected = new Info();
        expected.added = 3;
        expected.deleted = 0;
        expected.changed = 0;
        assertThat(info, is(expected));
    }

    @Test
    public void whenNothingChanged() {
        List<User> previous = new LinkedList<>(Arrays.asList(
                new User(1, "test1"),
                new User(2, "test2"),
                new User(3, "test3")
        ));

        List<User> current = new LinkedList<>(Arrays.asList(
                new User(1, "test1"),
                new User(2, "test2"),
                new User(3, "test3")
        ));

        Info info = Analyze.diff(previous, current);
        Info expected = new Info();
        expected.added = 0;
        expected.deleted = 0;
        expected.changed = 0;
        assertThat(info, is(expected));
    }

    @Test
    public void whenTwoEqualsOneDeletedOneAddedOneChanged() {
        List<User> previous = new LinkedList<>(Arrays.asList(
                new User(1, "a"),
                new User(2, "b"),
                new User(3, "c"),
                new User(4, "d")
        ));

        List<User> current = new LinkedList<>(Arrays.asList(
                new User(1, "a"),
                new User(2, "x"),
                new User(3, "c"),
                new User(5, "y")
        ));

        Info info = Analyze.diff(previous, current);
        Info expected = new Info();
        expected.added = 1;
        expected.deleted = 1;
        expected.changed = 1;
        assertThat(info, is(expected));
    }

}
package ru.job4j.job4jdesign.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleQueueTest {
    @Test
    public void whenPushThenPoll() {
        SimpleQueue<Integer> stack = new SimpleQueue<>();
        stack.push(1);
        assertThat(stack.poll(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleQueue<Integer> stack = new SimpleQueue<>();
        stack.push(1);
        stack.poll();
        stack.push(2);
        assertThat(stack.poll(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleQueue<Integer> stack = new SimpleQueue<>();
        stack.push(1);
        stack.push(2);
        stack.poll();
        assertThat(stack.poll(), is(2));
    }

    @Test
    public void whenFourPushThenFourPoll() {
        SimpleQueue<Integer> stack = new SimpleQueue<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.poll();
        stack.poll();
        stack.poll();
        assertThat(stack.poll(), is(4));
    }

    @Test
    public void whenFourPushThenOnePoll() {
        SimpleQueue<Integer> stack = new SimpleQueue<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertThat(stack.poll(), is(1));
    }

}
package ru.job4j.template;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class GeneratorTest {

    @Test
    public void testTemplateWithSingleArg() {
        String pattern = "Hello, ${single}!";
        Map<String, String> args = Map.of("single", "Single arg");
        String result = new SimpleGenerator().produce(pattern, args);
        String expected = "Hello, Single arg!";
        assertThat(result, is(expected));
    }

    @Test
    public void testTemplateWithMultipleArgs() {
        String pattern = "Hello, ${first} and ${second}!";
        Map<String, String> args = Map.of("first", "First arg",
                "second", "Other arg");
        String result = new SimpleGenerator().produce(pattern, args);
        String expected = "Hello, First arg and Other arg!";
        assertThat(result, is(expected));
    }

    @Test
    public void testTemplateWithMultipleArgsRandomOrder() {
        String pattern = "Hello, ${third}, ${first} and ${second}!";
        Map<String, String> args = Map.of("first", "First arg",
                "second", "Other arg",
                "third", "Yet one arg");
        String result = new SimpleGenerator().produce(pattern, args);
        String expected = "Hello, Yet one arg, First arg and Other arg!";
        assertThat(result, is(expected));
    }

    @Test
    public void testTemplateWithoutArg() {
        String pattern = "Hello, World!";
        Map<String, String> args = Map.of();
        String result = new SimpleGenerator().produce(pattern, args);
        String expected = "Hello, World!";
        assertThat(result, is(expected));
    }

    @Test
    public void testTemplateWithDuplicatedArgs() {
        String pattern = "Hello, ${single} and ${single}!";
        Map<String, String> args = Map.of("single", "John");
        String result = new SimpleGenerator().produce(pattern, args);
        String expected = "Hello, John and John!";
        assertThat(result, is(expected));
    }

}
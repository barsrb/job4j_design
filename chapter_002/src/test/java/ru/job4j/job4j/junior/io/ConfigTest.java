package ru.job4j.job4j.junior.io;

import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConfigTest {
    private String path = "../files/app.properties";

    @Test
    public void readConfig() {
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.password"),
                is("password")
        );
    }

    @Test
    public void getValueWithEqual() {
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("key.for.value.with.equals"),
                is("?user=123&pass=321")
        );
    }

}
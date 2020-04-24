package ru.job4j.job4j.junior.io;


import org.junit.Test;

public class ShellTest {

    @Test
    public void testCD() {
        final Shell shell = new Shell();
        assert shell.path().equals("/");

        shell.cd("/");
        assert shell.path().equals("/");

        shell.cd("usr/..");
        assert shell.path().equals("/");

        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assert shell.path().equals("/usr/local");

        shell.cd("..");
        assert shell.path().equals("/usr");

        shell.cd("//lib///");
        assert shell.path().equals("/lib");

        shell.cd("././var/shell/././../././../../home");
        assert shell.path().equals("/home");
    }

}
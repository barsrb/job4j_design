package ru.job4j.job4j.junior.io;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class FileListerTest {

    @Test
    public void testParametersWithFilename() {
        SearchParameters parameters = new SearchParameters("-d c:\\ -n filename.txt -f -o log.txt".split(" "));
        assertThat(parameters.getDirectory(), is("c:\\"));
        assertThat(parameters.getPattern(), is("^filename\\.txt$"));
        assertThat(parameters.getLogFile(), is("log.txt"));
    }

    @Test
    public void testParametersWithMaskAsterisk() {
        SearchParameters parameters = new SearchParameters("-d c:\\ -n *.txt -m -o log.txt".split(" "));
        assertThat(parameters.getDirectory(), is("c:\\"));
        assertThat(parameters.getPattern(), is("^.*\\.txt$"));
        assertThat(parameters.getLogFile(), is("log.txt"));
    }

    @Test
    public void testParametersWithMaskQuestion() {
        SearchParameters parameters = new SearchParameters("-d c:\\ -n f?le?.txt -m -o log.txt".split(" "));
        assertThat(parameters.getDirectory(), is("c:\\"));
        assertThat(parameters.getPattern(), is("^f.?le.?\\.txt$"));
        assertThat(parameters.getLogFile(), is("log.txt"));
    }

}
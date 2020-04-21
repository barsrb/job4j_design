package ru.job4j.junior.log4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4J {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4J.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}

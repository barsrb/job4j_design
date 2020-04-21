package ru.job4j.junior.log4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UsageLog4J {
    private static final Logger LOG = LogManager.getLogger(UsageLog4J.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}

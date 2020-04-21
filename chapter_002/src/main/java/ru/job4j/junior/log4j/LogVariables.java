package ru.job4j.junior.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogVariables {
    private static final Logger LOG = LoggerFactory.getLogger(LogVariables.class.getName());

    public static void main(String[] args) {

        boolean bol = true;

        byte b = 120;
        short s = 32635;
        char c = 'C';
        int i = 123456;
        long l = 123456789L;


        float f = 0.123456789F;
        double d = 0.987654321D;

        LOG.info("Boolean - {}", bol);

        LOG.warn("Byte - {}, short - {}, char - {}, int - {}, long - {}", b, s, c, i, l);

        LOG.error("Float - {}, double - {}", f, d);

    }
}

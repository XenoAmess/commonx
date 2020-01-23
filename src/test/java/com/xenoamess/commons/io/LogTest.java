package com.xenoamess.commons.io;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test() {
        LOGGER.debug("I'm fine.");
    }

    public static void main(String[] args) {
        new LogTest().test();
    }
}

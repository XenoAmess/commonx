package com.xenoamess.commons.primitive.collections.lists.array_lists;

import org.junit.jupiter.api.Test;

public class PerformanceTests {

    @Test
    public void performanceTest() {
        new BooleanArrayListTest().performanceChecks(100000);
        new ByteArrayListTest().performanceChecks(100000);
        new CharArrayListTest().performanceChecks(100000);
        new DoubleArrayListTest().performanceChecks(100000);
        new FloatArrayListTest().performanceChecks(100000);
        new IntArrayListTest().performanceChecks(100000);
        new LongArrayListTest().performanceChecks(100000);
        new ShortArrayListTest().performanceChecks(100000);
    }
}

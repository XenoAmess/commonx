package com.xenoamess.commonx.java.util.concurrent.atomic;

import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

/**
 * @author XenoAmess
 * @see java.util.concurrent.atomic.AtomicBoolean
 */
public class AtomicBooleanUtilsx {
    /**
     * <p>Instances of this class should NOT be constructed in standard programming.
     *
     * <p>This constructor is public to permit tools that require a JavaBean instance
     * to operate.
     */
    public AtomicBooleanUtilsx() {
        super();
    }

    /**
     * Flip the AtomicBoolean.
     * Sets the boolean value to false if it is true, and to true if it is false
     * with memory effects as specified by {@link VarHandle#setVolatile}.
     *
     * @param atomicBoolean atomicBoolean
     * @return new boolean value of AtomicBoolean
     * @see AtomicInteger#accumulateAndGet(int x, IntBinaryOperator accumulatorFunction)
     */
    public static final boolean flip(AtomicBoolean atomicBoolean) {
        boolean prev = atomicBoolean.get(), next = false;
        for (boolean haveNext = false; ; ) {
            if (!haveNext) {
                next = !prev;
            }
            if (atomicBoolean.weakCompareAndSetVolatile(prev, next)) {
                return next;
            }
            haveNext = (prev == (prev = atomicBoolean.get()));
        }
    }
}

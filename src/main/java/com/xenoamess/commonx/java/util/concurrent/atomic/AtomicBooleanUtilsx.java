/*
 * MIT License
 *
 * Copyright (c) 2019 XenoAmess
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.xenoamess.commonx.java.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

/**
 * <p>AtomicBooleanUtilsx class.</p>
 *
 * @author XenoAmess
 * @see java.util.concurrent.atomic.AtomicBoolean
 * @version $Id: $Id
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
     * with memory effects as specified by {@link java.lang.invoke.VarHandle#setVolatile}.
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

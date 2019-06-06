/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.xenoamess.commonx.java.util.concurrent.atomic;

import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

/**
 * <p>AtomicBooleanUtilsx class.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
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
     * with memory effects as specified by {@link java.lang.invoke.VarHandle#setVolatile}.
     *
     * @param atomicBoolean atomicBoolean
     * @return new boolean value of AtomicBoolean
     * @see AtomicInteger#accumulateAndGet(int x, IntBinaryOperator accumulatorFunction)
     * @since 9
     */
    public static final boolean flip(AtomicBoolean atomicBoolean) {
        if (SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_1_8)) {
            return flipForJava8(atomicBoolean);
        }
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

    /**
     * <p>
     * notice: Shall only use this in java 8.
     * <p>
     * Flip the AtomicBoolean.
     * <p>
     * this is a implement that does not use {@link java.lang.invoke.VarHandle#setVolatile} and
     * {@link java.util.concurrent.atomic.AtomicBoolean#weakCompareAndSetVolatile}.
     * Sets the boolean value to false if it is true, and to true if it is false
     * with memory effects as specified by {@link java.lang.invoke.VarHandle#setVolatile}.
     *
     * @param atomicBoolean atomicBoolean
     * @return new boolean value of AtomicBoolean
     * @see AtomicInteger#accumulateAndGet(int x, IntBinaryOperator accumulatorFunction)
     */
    public static final boolean flipForJava8(AtomicBoolean atomicBoolean) {
        boolean v;
        do {
            v = atomicBoolean.get();
        } while (!atomicBoolean.compareAndSet(v, !v));
        return !v;
    }
}

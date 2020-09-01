/*
 * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
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

package com.xenoamess.commons.primitive.iterators;

import com.xenoamess.commons.primitive.Primitive;
import com.xenoamess.commons.primitive.comparators.FloatComparator;
import com.xenoamess.commons.primitive.functions.FloatConsumer;
import java.util.Spliterator;

/**
 * A FloatSpliterator.FloatOfFloat designed for use by sources that traverse and split
 * elements maintained in an unmodifiable {@code int[]} array.
 *
 * @author XenoAmess
 * @version 0.8.0
 * @see FloatSpliterator
 */
public class FloatArraySpliterator implements FloatSpliterator.FloatOfFloat, Primitive {
    private final float[] array;
    private int index;        // current index, modified on advance/split
    private final int fence;  // one past last index
    private final int characteristics;

    /**
     * Creates a spliterator covering all of the given array.
     *
     * @param array                     the array, assumed to be unmodified during use
     * @param additionalCharacteristics Additional spliterator characteristics
     *                                  of this spliterator's source or elements beyond {@code SIZED} and
     *                                  {@code SUBSIZED} which are always reported
     */
    public FloatArraySpliterator(float[] array, int additionalCharacteristics) {
        this(array, 0, array.length, additionalCharacteristics);
    }

    /**
     * Creates a spliterator covering the given array and range
     *
     * @param array                     the array, assumed to be unmodified during use
     * @param origin                    the least index (inclusive) to cover
     * @param fence                     one past the greatest index to cover
     * @param additionalCharacteristics Additional spliterator characteristics
     *                                  of this spliterator's source or elements beyond {@code SIZED} and
     *                                  {@code SUBSIZED} which are always reported
     */
    public FloatArraySpliterator(float[] array, int origin, int fence, int additionalCharacteristics) {
        this.array = array;
        this.index = origin;
        this.fence = fence;
        this.characteristics = additionalCharacteristics | FloatSpliterator.SIZED | FloatSpliterator.SUBSIZED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FloatOfFloat trySplit() {
        int lo = index, mid = (lo + fence) >>> 1;
        return (lo >= mid)
                ? null
                : new FloatArraySpliterator(array, lo, index = mid, characteristics);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEachRemaining(FloatConsumer action) {
        float[] a;
        int i, hi; // hoist accesses and checks from loop
        if (action == null) {
            throw new NullPointerException();
        }
        if ((a = array).length >= (hi = fence) &&
                (i = index) >= 0 && i < (index = hi)) {
            do {
                action.accept(a[i]);
            } while (++i < hi);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tryAdvance(FloatConsumer action) {
        if (action == null) {
            throw new NullPointerException();
        }
        if (index >= 0 && index < fence) {
            action.accept(array[index++]);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long estimateSize() {
        return (long) (fence - index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int characteristics() {
        return characteristics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FloatComparator getComparator() {
        if (hasCharacteristics(Spliterator.SORTED)) {
            return null;
        }
        throw new IllegalStateException();
    }
}
